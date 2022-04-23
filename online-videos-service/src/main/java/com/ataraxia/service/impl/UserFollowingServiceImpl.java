package com.ataraxia.service.impl;

import com.ataraxia.domain.FollowingGroupDO;
import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.UserFollowingDO;
import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.domain.constant.UserConstant;
import com.ataraxia.domain.exception.ConditionException;
import com.ataraxia.mapper.UserFollowingMapper;
import com.ataraxia.service.FollowingGroupService;
import com.ataraxia.service.UserFollowingService;
import com.ataraxia.service.UserInfoService;
import com.ataraxia.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:19
 * @description 用户关注服务接口实现类
 */
@Service
public class UserFollowingServiceImpl extends ServiceImpl<UserFollowingMapper, UserFollowingDO> implements UserFollowingService {

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户关注、更新
     * @param userFollowing 关注分组信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserFollowings(UserFollowingDO userFollowing) {
        Long groupId = userFollowing.getGroupId();
        // 如果没有分组ID，说明是默认分组
        if (Objects.isNull(groupId)) {
            FollowingGroupDO followingGroup = followingGroupService.getByType(UserConstant.USER_FOLLOWING_GROUP_TYPE_DEFAULT);
            userFollowing.setGroupId(followingGroup.getId());
        } else {
            FollowingGroupDO followingGroup = followingGroupService.getById(groupId);
            if (Objects.isNull(followingGroup)) {
                throw new ConditionException("关注分组不存在！");
            }
        }

        Long followingId = userFollowing.getFollowingId();
        UserDO user = userService.getById(followingId);
        if (Objects.isNull(user)) {
            throw new ConditionException("关注的用户不存在！");
        }

        // 先删除
        baseMapper.deleteUserFollowing(userFollowing.getUserId(), followingId);

        // 再更新
        userFollowing.setCreateTime(new Date());
        baseMapper.insert(userFollowing);

    }

    /**
     * 1、获取关注的用户列表
     * 2、根据关注用户的ID查询关注用户的基本信息
     * 3、将关注用户按关注分组进行分类
     * @param userId
     * @return
     */
    @Override
    public List<FollowingGroupDO> listUserFollowings(Long userId) {
        LambdaQueryWrapper<UserFollowingDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollowingDO::getUserId, userId);
        List<UserFollowingDO> userFollowingList = baseMapper.selectList(wrapper);

        // 抽出followingId
        Set<Long> followingIdSet = userFollowingList.stream().map(UserFollowingDO::getFollowingId).collect(Collectors.toSet());
        List<UserInfoDO> userInfoList = new ArrayList<>();
        if (followingIdSet.size() > 0) {
            // 查对应所有参数的信息
            userInfoList = userInfoService.listUserInfoByUserIds(followingIdSet);
        }

        for (UserFollowingDO userFollowing : userFollowingList) {
            for (UserInfoDO userInfo : userInfoList) {
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())) {
                    userFollowing.setUserInfo(userInfo);
                }
            }
        }

        // 分组
        List<FollowingGroupDO> groupList = followingGroupService.listByUserId(userId);

        // 全部分组
        FollowingGroupDO allGroup = new FollowingGroupDO();
        allGroup.setName(UserConstant.USER_FOLLOWING_GROUP_ALL_NAME);
        allGroup.setFollowingUserInfoList(userInfoList);

        List<FollowingGroupDO> result = new ArrayList<>();
        result.add(allGroup);

        for (FollowingGroupDO group : groupList) {
            List<UserInfoDO> infoList = new ArrayList<>();
            for (UserFollowingDO userFollowingDO : userFollowingList) {
                if (group.getId().equals(userFollowingDO.getGroupId())) {
                    infoList.add(userFollowingDO.getUserInfo());
                }
            }
            group.setFollowingUserInfoList(infoList);
            result.add(group);
        }

        return result;
    }

    /**
     * 获取用户粉丝列表
     * 1、获取当前用户的粉丝列表
     * 2、根据粉丝的用户ID查询基本信息
     * 3、查询当前用户是否已经关注该粉丝
     * @param userId 用户ID
     * @return 粉丝列表
     */
    @Override
    public List<UserFollowingDO> listUserFans(Long userId) {
        LambdaQueryWrapper<UserFollowingDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollowingDO::getFollowingId, userId);
        List<UserFollowingDO> fanList = baseMapper.selectList(wrapper);

        // 抽出followingId
        Set<Long> fanIdSet = fanList.stream().map(UserFollowingDO::getUserId).collect(Collectors.toSet());
        List<UserInfoDO> userInfoList = new ArrayList<>();

        if (fanIdSet.size() > 0) {
            userInfoList = userInfoService.listUserInfoByUserIds(fanIdSet);
        }

        // 判断当前粉丝列表有没有登录用户关注过的
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollowingDO::getUserId, userId);
        List<UserFollowingDO> followings = baseMapper.selectList(wrapper);

        for (UserFollowingDO fan : fanList) {
            for (UserInfoDO userInfo : userInfoList) {
                // 初始化
                if (fan.getUserId().equals(userInfo.getUserId())) {
                    userInfo.setFollowed(false);
                    fan.setUserInfo(userInfo);
                }
            }
            for (UserFollowingDO following : followings) {
                // 互相关注的状态
                if (following.getFollowingId().equals(fan.getUserId())) {
                    fan.getUserInfo().setFollowed(true);
                }
            }
        }

        return fanList;
    }

    /**
     * 校验列表中是否存在关注用户
     * @param userInfoList 用户信息列表
     * @param userId 登录用户ID
     * @return 校验过的用户信息列表
     */
    @Override
    public List<UserInfoDO> listCheckFollowingStatus(List<UserInfoDO> userInfoList, Long userId) {
        // 获取用户关注列表
        LambdaQueryWrapper<UserFollowingDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollowingDO::getUserId, userId);
        List<UserFollowingDO> userFollowingList = baseMapper.selectList(wrapper);

        for (UserInfoDO userInfo : userInfoList) {
            userInfo.setFollowed(false);
            for(UserFollowingDO userFollowing : userFollowingList){
                if(userFollowing.getFollowingId().equals(userInfo.getUserId())){
                    userInfo.setFollowed(true);
                }
            }
        }
        return userInfoList;
    }


}
