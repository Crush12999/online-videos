package com.ataraxia.repository;

import com.ataraxia.domain.UserInfoDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ataraxia
 * @create 2022/4/26 12:11
 * @description
 */
public interface UserInfoRepository extends ElasticsearchRepository<UserInfoDO, Long> {

}
