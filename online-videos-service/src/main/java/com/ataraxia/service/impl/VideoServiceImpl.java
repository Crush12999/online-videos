package com.ataraxia.service.impl;

import com.ataraxia.domain.PageResult;
import com.ataraxia.domain.VideoLikeDO;
import com.ataraxia.domain.VideoTagDO;
import com.ataraxia.domain.exception.ConditionException;
import com.ataraxia.service.VideoLikeService;
import com.ataraxia.service.VideoService;
import com.ataraxia.util.FastDFSUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ataraxia.domain.VideoDO;
import com.ataraxia.mapper.VideoMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author chuchen
 * @description 针对表【t_video(视频投稿记录表)】的数据库操作Service实现
 * @createDate 2022-04-24 22:37:30
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO>
        implements VideoService {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private VideoLikeService videoLikeService;

    /**
     * 视频投稿
     *
     * @param video 视频
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveVideo(VideoDO video) {
        Date now = new Date();
        video.setCreateTime(now);
        baseMapper.insert(video);
        Long videoId = video.getId();
        List<VideoTagDO> tags = video.getVideoTagList();
        tags.forEach(item -> {
            item.setCreateTime(now);
            item.setVideoId(videoId);
        });
        baseMapper.batchSaveVideoTags(tags);
    }

    /**
     * 分页查询首页视频瀑布流
     *
     * @param size 一页显示几条
     * @param no   第几页
     * @param area 分区
     * @return 视频瀑布流分页信息
     */
    @Override
    public PageResult<VideoDO> pageListVideos(Integer size, Integer no, String area) {
        if (Objects.isNull(size) || Objects.isNull(no)) {
            throw new ConditionException("参数异常！");
        }

        LambdaQueryWrapper<VideoDO> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(area)) {
            wrapper.eq(VideoDO::getArea, area);
        }
        wrapper.orderByDesc(VideoDO::getId);
        Page<VideoDO> page = new Page<>(no, size);

        IPage<VideoDO> pageData = baseMapper.selectPage(page, wrapper);
        List<VideoDO> list = pageData.getRecords();

        return new PageResult<>(page.getTotal(), list);
    }

    /**
     * 视频在线观看
     * @param request
     * @param response
     * @param url
     */
    @Override
    public void viewVideoOnlineBySlices(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String url) throws Exception {

        fastDFSUtil.viewVideoOnlineBySlices(request, response, url);
    }

    /**
     * 视频点赞功能
     * @param videoId 对应视频
     * @param userId 用户
     */
    @Override
    public void saveVideoLike(Long videoId, Long userId) {
        VideoDO video = baseMapper.selectById(videoId);
        if (Objects.isNull(video)) {
            throw new ConditionException("非法视频！");
        }
        VideoLikeDO videoLike = videoLikeService.getVideoLikeByVideoIdAndUserId(videoId, userId);
        if (Objects.nonNull(videoLike)) {
            throw new ConditionException("视频已点赞！");
        }
        videoLike = new VideoLikeDO();
        videoLike.setVideoId(videoId);
        videoLike.setUserId(userId);
        videoLike.setCreateTime(new Date());
        videoLikeService.save(videoLike);
    }

    /**
     * 取消视频点赞
     * @param videoId 对应视频
     * @param userId 用户
     */
    @Override
    public void deleteVideoLike(Long videoId, Long userId) {
        videoLikeService.deleteVideoLike(videoId, userId);
    }

    /**
     * 获取视频点赞数量
     * @param videoId 视频id
     * @param userId 用户id
     * @return 相关信息
     */
    @Override
    public Map<String, Object> getVideoLikes(Long videoId, Long userId) {
        int count = videoLikeService.getVideoLikes(videoId);
        VideoLikeDO videoLike = videoLikeService.getVideoLikeByVideoIdAndUserId(videoId, userId);
        boolean like = videoLike != null;
        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("like", like);
        return result;
    }


}




