package com.ataraxia.repository;

import com.ataraxia.domain.VideoDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ataraxia
 * @create 2022/4/26 12:11
 * @description
 */
public interface VideoRepository extends ElasticsearchRepository<VideoDO, Long> {

    /**
     * 通过视频标题进行模糊查询
     *
     * @param keyword 关键词
     * @return 对应视频
     */
    VideoDO findByTitleLike(String keyword);
}
