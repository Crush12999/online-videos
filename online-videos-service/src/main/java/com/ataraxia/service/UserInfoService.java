package com.ataraxia.service;

import com.ataraxia.domain.UserInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author Ataraxia
 * @create 2022/4/21 11:18
 * @description 用户基本信息
 */
public interface UserInfoService extends IService<UserInfoDO> {
    /**
     * 更新用户信息
     * @param userInfo
     */
    void updateUserInfos(UserInfoDO userInfo);

    /**
     * 通过id集合查询对应用户信息
     * @param followingIdSet
     * @return
     */
    List<UserInfoDO> getUserInfoByUserIds(Set<Long> followingIdSet);
}
