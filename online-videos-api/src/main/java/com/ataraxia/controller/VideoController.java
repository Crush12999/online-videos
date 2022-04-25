package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.*;
import com.ataraxia.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
     *
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

    @PostMapping("/video-likes")
    @ApiOperation(value = "视频点赞")
    public ResponseResult<String> saveVideoLike(@RequestParam Long videoId) {
        Long userId = userSupport.getCurrentUserId();
        videoService.saveVideoLike(videoId, userId);
        return ResponseResult.success();
    }

    @DeleteMapping("/video-likes")
    @ApiOperation(value = "取消视频点赞")
    public ResponseResult<String> deleteVideoLike(@RequestParam Long videoId) {
        Long userId = userSupport.getCurrentUserId();
        videoService.deleteVideoLike(videoId, userId);
        return ResponseResult.success();
    }

    @GetMapping("/video-likes")
    @ApiOperation(value = "获取视频点赞数量")
    public ResponseResult<Map<String, Object>> getVideoLikes(@RequestParam Long videoId) {
        // 在游客模式下也可以看视频
        Long userId = null;
        try {
            // 因为调用这个方法会校验token、可以在这捕捉异常，捕捉到说明用户没登录，也不影响观看，如果登录了也一样
            userId = userSupport.getCurrentUserId();
        } catch (Exception ignored) {
        }

        Map<String, Object> result = videoService.getVideoLikes(videoId, userId);
        return new ResponseResult<>(result);
    }

    @PostMapping("/video-collections")
    @ApiOperation(value = "收藏视频")
    public ResponseResult<String> saveVideoCollection(@RequestBody VideoCollectionDO videoCollection) {
        Long userId = userSupport.getCurrentUserId();
        videoService.saveVideoCollection(videoCollection, userId);
        return ResponseResult.success();
    }

    @DeleteMapping("/video-collections")
    @ApiOperation(value = "取消收藏视频")
    public ResponseResult<String> deleteVideoCollection(@RequestParam Long videoId) {
        Long userId = userSupport.getCurrentUserId();
        videoService.deleteVideoCollection(videoId, userId);
        return ResponseResult.success();
    }

    @GetMapping("/video-collections")
    @ApiOperation(value = "查询视频收藏数量")
    public ResponseResult<Map<String, Object>> getVideoCollections(@RequestParam Long videoId) {
        Long userId = null;
        try {
            userId = userSupport.getCurrentUserId();
        } catch (Exception ignored) {
        }
        Map<String, Object> result = videoService.getVideoCollections(videoId, userId);
        return new ResponseResult<>(result);
    }


    @PostMapping("/video-coins")
    @ApiOperation(value = "视频投币")
    public ResponseResult<String> saveVideoCoins(@RequestBody VideoCoinDO videoCoin) {
        Long userId = userSupport.getCurrentUserId();
        videoService.saveVideoCoins(videoCoin, userId);
        return ResponseResult.success();
    }


    @GetMapping("/video-coins")
    @ApiOperation(value = "查询视频投币数量")
    public ResponseResult<Map<String, Object>> getVideoCoins(@RequestParam Long videoId) {
        Long userId = null;
        try {
            userId = userSupport.getCurrentUserId();
        } catch (Exception ignored) {
        }
        Map<String, Object> result = videoService.getVideoCoins(videoId, userId);
        return new ResponseResult<>(result);
    }

    @PostMapping("/video-comments")
    @ApiOperation(value = "发布视频评论")
    public ResponseResult<String> saveVideoComment(@RequestBody VideoCommentDO videoComment) {
        Long userId = userSupport.getCurrentUserId();
        videoService.saveVideoComment(videoComment, userId);
        return ResponseResult.success();
    }

    /**
     * 分页查询视频评论
     */
    @GetMapping("/video-comments")
    public ResponseResult<PageResult<VideoCommentDO>> pageListVideoComments(@RequestParam Integer size,
                                                                          @RequestParam Integer no,
                                                                          @RequestParam Long videoId) {
        PageResult<VideoCommentDO> result = videoService.pageListVideoComments(size, no, videoId);
        return new ResponseResult<>(result);
    }

}
