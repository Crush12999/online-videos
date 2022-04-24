package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.VideoDO;
import com.ataraxia.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ataraxia
 * @create 2022/4/24 22:41
 * @description 视频接口
 */
@Api(tags = {"视频接口"})
@RestController
public class VideoController {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private VideoService videoService;

    @PostMapping("/videos")
    @ApiOperation(value = "视频投稿")
    public ResponseResult<String> saveVideo(@RequestBody VideoDO video) {
        Long userId = userSupport.getCurrentUserId();
        video.setUserId(userId);
        videoService.saveVideo(video);
        return ResponseResult.success();
    }
}
