package com.ataraxia.service.impl;

import com.ataraxia.domain.VideoTagDO;
import com.ataraxia.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ataraxia.domain.VideoDO;
import com.ataraxia.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @author chuchen
* @description 针对表【t_video(视频投稿记录表)】的数据库操作Service实现
* @createDate 2022-04-24 22:37:30
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO>
    implements VideoService {

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
}




