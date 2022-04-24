package com.ataraxia.service;

import com.ataraxia.domain.FileDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ataraxia
 * @create 2022/4/24 20:48
 * @description 文件上传业务处理接口
 */
public interface FileService extends IService<FileDO> {
    /**
     * 文件分片上传
     *
     * @param slice        文件分片
     * @param fileMd5      文件唯一标识
     * @param sliceNo      当前分片序号
     * @param totalSliceNo 总分片数
     * @return 上传成功后的路径
     */
    String uploadFileBySlices(MultipartFile slice,
                              String fileMd5,
                              Integer sliceNo,
                              Integer totalSliceNo);

    /**
     * 获取文件MD5
     * @param file 文件
     * @return md5加密字符串
     */
    String getFileMd5(MultipartFile file) throws IOException;
}
