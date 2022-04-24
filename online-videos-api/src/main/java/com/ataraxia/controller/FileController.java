package com.ataraxia.controller;

import com.ataraxia.domain.ResponseResult;
import com.ataraxia.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ataraxia
 * @create 2022/4/24 20:44
 * @description 文件上传接口
 */
@Api(tags = {"文件上传接口"})
@RestController
public class FileController {
    
    @Autowired
    private FileService fileService;

    @PostMapping("/md5files")
    public ResponseResult<String> getFileMd5(MultipartFile file) throws IOException {
        String fileMd5 = fileService.getFileMd5(file);
        return new ResponseResult<>(fileMd5);
    }

    @PutMapping("/file-slices")
    @ApiOperation(value = "文件断点续传")
    public ResponseResult<String> uploadFileBySlices(MultipartFile slice,
                                                     String fileMd5,
                                                     Integer sliceNo,
                                                     Integer totalSliceNo) {
        String filePath = fileService.uploadFileBySlices(slice, fileMd5, sliceNo, totalSliceNo);
        return new ResponseResult<>(filePath);
    }
}
