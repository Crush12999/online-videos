package com.ataraxia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ataraxia.domain.VideoLikeDO;
import com.ataraxia.mapper.VideoLikeMapper;
import com.ataraxia.service.VideoLikeService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author chuchen
 * @description 针对表【t_video_like(视频点赞记录表)】的数据库操作Service实现
 * @createDate 2022-04-25 00:22:18
 */
@Service
public class VideoLikeServiceImpl extends ServiceImpl<VideoLikeMapper, VideoLikeDO>
        implements VideoLikeService {

    /**
     * 通过视频ID和用户ID获取视频点赞信息
     *
     * @param videoId 视频id
     * @param userId  用户id
     * @return 视频点赞信息
     */
    @Override
    public VideoLikeDO getVideoLikeByVideoIdAndUserId(Long videoId, Long userId) {
        LambdaQueryWrapper<VideoLikeDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VideoLikeDO::getUserId, userId);
        wrapper.eq(VideoLikeDO::getVideoId, videoId);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 取消点赞
     *
     * @param videoId 视频id
     * @param userId  用户id
     */
    @Override
    public void deleteVideoLike(Long videoId, Long userId) {
        LambdaQueryWrapper<VideoLikeDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VideoLikeDO::getUserId, userId);
        wrapper.eq(VideoLikeDO::getVideoId, videoId);
        baseMapper.delete(wrapper);
    }

    /**
     * 获取视频用户点赞数量
     *
     * @param videoId 视频id
     * @return 点赞数
     */
    @Override
    public Integer getVideoLikes(Long videoId) {
        LambdaQueryWrapper<VideoLikeDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VideoLikeDO::getVideoId, videoId);
        return baseMapper.selectCount(wrapper);
    }
}




