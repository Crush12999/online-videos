package com.ataraxia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ataraxia.domain.VideoTagDO;
import com.ataraxia.mapper.VideoTagMapper;
import com.ataraxia.service.VideoTagService;
import org.springframework.stereotype.Service;

/**
* @author chuchen
* @description 针对表【t_video_tag(视频标签关联表)】的数据库操作Service实现
* @createDate 2022-04-24 22:47:06
*/
@Service
public class VideoTagServiceImpl extends ServiceImpl<VideoTagMapper, VideoTagDO>
    implements VideoTagService{

}




