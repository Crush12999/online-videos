package com.ataraxia.util;

import com.ataraxia.domain.exception.ConditionException;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author Ataraxia
 * @create 2022/4/24 19:10
 * @description FastDFS工具类
 */
public class FastDFSUtil {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private AppendFileStorageClient appendFileStorageClient;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String DEFAULT_GROUP = "group1";

    private static final String PATH_KEY = "path-key:";

    private static final String UPLOADED_SIZE_KEY = "uploaded-size-key:";

    private static final String UPLOADED_NO_KEY = "uploaded-no-key:";

    /**
     * 获取文件类型
     *
     * @param file 文件
     * @return 文件类型
     */
    public String getFileType(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new ConditionException("非法文件！");
        }
        String fileName = file.getOriginalFilename();
        int index = Objects.requireNonNull(fileName).lastIndexOf(".");
        return fileName.substring(index + 1);
    }

    /**
     * 上传
     *
     * @param file 上传文件
     * @return 返回文件所在路径
     */
    public String uploadCommonFile(MultipartFile file) {
        Set<MetaData> metaDataSet = new HashSet<>();
        String fileType = getFileType(file);
        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), fileType, metaDataSet);
        } catch (IOException e) {
            throw new ConditionException("上传失败！");
        }
        return storePath.getPath();
    }

    /**
     * 上传可以断点续传的文件
     *
     * @param file 文件
     * @return 文件路径
     */
    public String uploadAppenderFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = getFileType(file);
        StorePath storePath = null;
        try {
            storePath = appendFileStorageClient.uploadAppenderFile(DEFAULT_GROUP, file.getInputStream(), file.getSize(), fileType);
        } catch (IOException e) {
            throw new ConditionException("上传失败！");
        }
        return storePath.getPath();
    }

    /**
     * 追加文件分片内容
     *
     * @param file     文件
     * @param filePath 文件路径
     * @param offset   偏移量，从哪里开始续传
     */
    public void modifyAppenderFile(MultipartFile file, String filePath, long offset) {
        try {
            appendFileStorageClient.modifyFile(DEFAULT_GROUP, filePath,
                    file.getInputStream(), file.getSize(), offset);
        } catch (IOException e) {
            throw new ConditionException("上传失败！");
        }
    }

    /**
     * 完整的文件续传
     *
     * @param file         待上传文件
     * @param fileMd5      文件的唯一标识
     * @param sliceNo      当前是文件的第几个分片
     * @param totalSliceNo 文件总共的分片数
     * @return 文件路径
     */
    public String uploadFileBySlices(MultipartFile file, String fileMd5, Integer sliceNo, Integer totalSliceNo) {
        if (Objects.isNull(file) || Objects.isNull(sliceNo) || Objects.isNull(totalSliceNo)) {
            throw new ConditionException("参数异常！");
        }
        // 第一个分片上传时返回的路径，存在redis中，等待所有分片上传成功后再清空
        String pathKey = PATH_KEY + fileMd5;
        // 当前上传的分片总大小
        String uploadedSizeKey = UPLOADED_SIZE_KEY + fileMd5;
        // 已经上传的分片数
        String uploadedNoKey = UPLOADED_NO_KEY + fileMd5;

        String uploadedSizeStr = redisTemplate.opsForValue().get(uploadedSizeKey);
        Long uploadedSize = 0L;
        if (!StringUtils.isNullOrEmpty(uploadedSizeStr)) {
            uploadedSize = Long.valueOf(uploadedSizeStr);
        }

        // 对传入的文件分片进行处理
        String fileType = this.getFileType(file);

        // 上传的是第一个分片
        if (sliceNo == 1) {
            String path = this.uploadAppenderFile(file);
            if (StringUtils.isNullOrEmpty(path)) {
                throw new ConditionException("上传失败！");
            }
            redisTemplate.opsForValue().set(pathKey, path);
            // 记录已上传的文件分片号
            redisTemplate.opsForValue().set(uploadedNoKey, "1");
        } else {
            String filePath = redisTemplate.opsForValue().get(pathKey);
            if (StringUtils.isNullOrEmpty(filePath)) {
                throw new ConditionException("上传失败！");
            }
            this.modifyAppenderFile(file, filePath, uploadedSize);
            // 对分片数进行+1操作
            redisTemplate.opsForValue().increment(uploadedNoKey);
        }
        // 修改历史已上传的文件大小
        uploadedSize += file.getSize();
        redisTemplate.opsForValue().set(uploadedSizeKey, String.valueOf(uploadedSize));
        // 如果所有分片全部上传完毕，则清空redis中相关的键值对
        String uploadedNoStr = redisTemplate.opsForValue().get(uploadedNoKey);
        Integer uploadedNo = Integer.parseInt(uploadedNoStr);
        String resultPath = "";
        if (uploadedNo.equals(totalSliceNo)) {
            resultPath = redisTemplate.opsForValue().get(pathKey);
            List<String> keyList = Arrays.asList(uploadedNoKey, pathKey, uploadedSizeKey);
            redisTemplate.delete(keyList);
        }
        return resultPath;
    }


    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    public void deleteFile(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
    }

}
