package com.ataraxia.service.impl;

import com.ataraxia.domain.FileDO;
import com.ataraxia.mapper.FileMapper;
import com.ataraxia.service.FileService;
import com.ataraxia.util.FastDFSUtil;
import com.ataraxia.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;


/**
 * @author Ataraxia
 * @create 2022/4/24 20:49
 * @description 文件上传业务处理接口实现类
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDO> implements FileService {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    /**
     * 文件分片上传
     *
     * @param slice        文件分片
     * @param fileMd5      文件唯一标识
     * @param sliceNo      当前分片序号
     * @param totalSliceNo 总分片数
     * @return 上传成功后的路径
     */
    @Override
    public String uploadFileBySlices(MultipartFile slice,
                                     String fileMd5,
                                     Integer sliceNo,
                                     Integer totalSliceNo) {

        FileDO dbFileMd5 = this.uploadFileByMd5(fileMd5);
        if (!Objects.isNull(dbFileMd5)) {
            return dbFileMd5.getUrl();
        }
        String url = fastDFSUtil.uploadFileBySlices(slice, fileMd5, sliceNo, totalSliceNo);
        if (!StringUtils.isNullOrEmpty(url)) {
            dbFileMd5 = new FileDO();
            dbFileMd5.setCreateTime(new Date());
            dbFileMd5.setMd5(fileMd5);
            dbFileMd5.setType(fastDFSUtil.getFileType(slice));
            baseMapper.insert(dbFileMd5);
        }

        return url;
    }

    /**
     * 获取文件MD5
     *
     * @param file 文件
     * @return md5加密字符串
     */
    @Override
    public String getFileMd5(MultipartFile file) throws IOException {
        return MD5Util.getFileMD5(file);
    }

    /**
     * 通过md5查询已上传文件
     * @param fileMd5 文件唯一标识符
     * @return 文件实体
     */
    private FileDO uploadFileByMd5(String fileMd5) {
        LambdaQueryWrapper<FileDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileDO::getMd5, fileMd5);
        return baseMapper.selectOne(wrapper);
    }
}
