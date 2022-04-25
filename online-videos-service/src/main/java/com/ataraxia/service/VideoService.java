package com.ataraxia.service;

import com.ataraxia.domain.PageResult;
import com.ataraxia.domain.VideoCollectionDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ataraxia.domain.VideoDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chuchen
 * @description 针对表【t_video(视频投稿记录表)】的数据库操作Service
 * @createDate 2022-04-24 22:37:30
 */
public interface VideoService extends IService<VideoDO> {

    /**
     * 视频投稿
     *
     * @param video 视频
     */
    void saveVideo(VideoDO video);

    /**
     * 分页查询首页视频瀑布流
     *
     * @param size 一页显示几条
     * @param no   第几页
     * @param area 分区
     * @return 视频瀑布流分页信息
     */
    PageResult<VideoDO> pageListVideos(Integer size, Integer no, String area);

    /**
     * 视频在线观看
     *
     * @param request
     * @param response
     * @param url
     * @throws Exception
     */
    void viewVideoOnlineBySlices(HttpServletRequest request, HttpServletResponse response, String url) throws Exception;

    /**
     * 视频点赞功能
     *
     * @param videoId 对应视频
     * @param userId  用户
     */
    void saveVideoLike(Long videoId, Long userId);

    /**
     * 取消视频点赞
     *
     * @param videoId 对应视频
     * @param userId  用户
     */
    void deleteVideoLike(Long videoId, Long userId);

    /**
     * 获取视频点赞数量
     *
     * @param videoId 视频id
     * @param userId  用户id
     * @return 相关信息
     */
    Map<String, Object> getVideoLikes(Long videoId, Long userId);

    /**
     * 收藏视频
     *
     * @param videoCollection 视频收藏信息实例
     * @param userId          用户id
     */
    void saveVideoCollection(VideoCollectionDO videoCollection, Long userId);

    /**
     * 取消收藏视频
     *
     * @param videoId 视频id
     * @param userId  用户id
     */
    void deleteVideoCollection(Long videoId, Long userId);

    /**
     * 查询视频收藏数以及当前用户是否已收藏视频
     *
     * @param videoId 视频id
     * @param userId  用户id
     * @return 相应数据
     */
    Map<String, Object> getVideoCollections(Long videoId, Long userId);
}