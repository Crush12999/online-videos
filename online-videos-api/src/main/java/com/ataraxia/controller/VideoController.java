package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.PageResult;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.VideoDO;
import com.ataraxia.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 分页查询首页视频瀑布流
     *
     * @param size 一页显示几条
     * @param no   第几页
     * @param area 分区
     * @return 视频瀑布流分页信息
     */
    @GetMapping("/videos")
    @ApiOperation(value = "分页查询首页视频瀑布流")
    public ResponseResult<PageResult<VideoDO>> pageListVideos(Integer size, Integer no, String area) {
        PageResult<VideoDO> result = videoService.pageListVideos(size, no, area);
        return new ResponseResult<>(result);
    }

    /**
     * 视频在线观看
     * @param request
     * @param response
     * @param url
     */
    @GetMapping("/video-slices")
    @ApiOperation(value = "视频在线观看")
    public void viewVideoOnlineBySlices(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String url) throws Exception {
        videoService.viewVideoOnlineBySlices(request, response, url);
    }

}
