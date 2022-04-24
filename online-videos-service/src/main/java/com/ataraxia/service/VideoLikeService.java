package com.ataraxia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ataraxia.domain.VideoLikeDO;

/**
* @author chuchen
* @description 针对表【t_video_like(视频点赞记录表)】的数据库操作Service
* @createDate 2022-04-25 00:22:18
*/
public interface VideoLikeService extends IService<VideoLikeDO> {

    /**
     * 通过视频ID和用户ID获取视频点赞信息
     * @param videoId 视频id
     * @param userId 用户id
     * @return 视频点赞信息
     */
    VideoLikeDO getVideoLikeByVideoIdAndUserId(Long videoId, Long userId);

    /**
     * 取消点赞
     * @param videoId 视频id
     * @param userId 用户id
     */
    void deleteVideoLike(Long videoId, Long userId);

    /**
     * 获取视频用户点赞数量
     * @param videoId 视频id
     * @return 点赞数
     */
    Integer getVideoLikes(Long videoId);
}
