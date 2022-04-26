package com.ataraxia.service;

import com.ataraxia.domain.VideoDO;
import com.ataraxia.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ataraxia
 * @create 2022/4/26 12:10
 * @description
 */
@Service
public class ElasticSearchService {

    @Autowired
    private VideoRepository videoRepository;

    /**
     * 将数据添加到es
     *
     * @param video 视频数据
     */
    public void saveVideo(VideoDO video) {
        videoRepository.save(video);
    }

    /**
     * 按关键词模糊查询
     *
     * @param keyword 模糊关键词
     */
    public VideoDO getVideos(String keyword) {
        return videoRepository.findByTitleLike(keyword);
    }

    public void deleteAllVideos() {
        videoRepository.deleteAll();
    }
}
