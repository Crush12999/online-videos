package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.FollowingGroupDO;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserFollowingDO;
import com.ataraxia.service.FollowingGroupService;
import com.ataraxia.service.UserFollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:54
 * @description
 */
@RestController
public class UserFollowingController {

    @Autowired
    private UserFollowingService userFollowingService;

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private UserSupport userSupport;

    /**
     * 关注用户
     * @param userFollowing 关注信息
     * @return 结果
     */
    @PostMapping("/user-followings")
    public ResponseResult<String> saveUserFollowings(@RequestBody UserFollowingDO userFollowing) {
        Long userId = userSupport.getCurrentUserId();
        userFollowing.setUserId(userId);
        userFollowingService.saveUserFollowings(userFollowing);

        return ResponseResult.success();
    }

    /**
     * 获取关注列表
     * @return 关注列表
     */
    @GetMapping("/user-followings")
    public ResponseResult<List<FollowingGroupDO>> getUserFollowings() {
        Long userId = userSupport.getCurrentUserId();
        List<FollowingGroupDO> result = userFollowingService.getUserFollowings(userId);
        return new ResponseResult<>(result);
    }


    /**
     * 获取粉丝列表
     * @return 粉丝列表
     */
    @GetMapping("/user-fans")
    public ResponseResult<List<UserFollowingDO>> getUserFans() {
        Long userId = userSupport.getCurrentUserId();
        List<UserFollowingDO> result = userFollowingService.getUserFans(userId);
        return new ResponseResult<>(result);
    }

    /**
     * 添加用户分组
     * @param followingGroup 分组信息
     * @return 添加的分组ID
     */
    @PostMapping("/user-following-groups")
    public ResponseResult<Long> saveUserFollowingGroups(@RequestBody FollowingGroupDO followingGroup) {
        Long userId = userSupport.getCurrentUserId();
        followingGroup.setUserId(userId);

        Long groupId = followingGroupService.saveUserFollowingGroups(followingGroup);
        return new ResponseResult<>(groupId);
    }

    /**
     * 查询用户分组
     * @return 用户分组信息
     */
    @GetMapping("/user-following-groups")
    public ResponseResult<List<FollowingGroupDO>> getUserFollowingGroups() {
        Long userId = userSupport.getCurrentUserId();
        List<FollowingGroupDO> list = followingGroupService.getUserFollowingGroups(userId);
        return new ResponseResult<>(list);
    }

}
