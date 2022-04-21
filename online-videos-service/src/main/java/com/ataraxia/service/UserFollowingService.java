package com.ataraxia.service;

import com.ataraxia.domain.FollowingGroupDO;
import com.ataraxia.domain.UserFollowingDO;
import com.ataraxia.domain.UserInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:18
 * @description 用户关注服务接口
 */
public interface UserFollowingService extends IService<UserFollowingDO> {

    /**
     * 用户关注、更新
     * @param userFollowing 关注分组信息
     */
    void saveUserFollowings(UserFollowingDO userFollowing);

    /**
     * 获取关注用户列表
     * @param userId 用户ID
     * @return 关注列表
     */
    List<FollowingGroupDO> getUserFollowings(Long userId);

    /**
     * 获取用户粉丝列表
     * @param userId 用户ID
     * @return 粉丝列表
     */
    List<UserFollowingDO> getUserFans(Long userId);

    /**
     * 校验列表中是否存在关注用户
     * @param list 用户信息列表
     * @param userId 登录用户ID
     * @return 校验过的用户信息列表
     */
    List<UserInfoDO> checkFollowingStatus(List<UserInfoDO> list, Long userId);
}
