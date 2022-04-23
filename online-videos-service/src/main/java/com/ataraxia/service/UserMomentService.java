package com.ataraxia.service;

import com.ataraxia.domain.UserMomentDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chuchen
* @description 针对表【t_user_moment(用户动态表)】的数据库操作Service
* @createDate 2022-04-23 00:00:14
*/
public interface UserMomentService extends IService<UserMomentDO> {

    /**
     * 添加用户动态
     * @param userMoment 用户动态
     * @throws Exception MQ处理异常
     */
    void insertUserMoments(UserMomentDO userMoment) throws Exception;

    /**
     * 查询用户关注的人的相关动态信息
     * @param userId 当前登录用户ID
     * @return 用户关注的人的相关动态信息列表
     */
    List<UserMomentDO> listUserSubscribedMoments(Long userId);
}
