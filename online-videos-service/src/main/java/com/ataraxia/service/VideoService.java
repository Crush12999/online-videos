package com.ataraxia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ataraxia.domain.VideoDO;

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
}
