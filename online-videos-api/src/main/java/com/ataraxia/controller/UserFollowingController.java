package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.FollowingGroupDO;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserFollowingDO;
import com.ataraxia.service.FollowingGroupService;
import com.ataraxia.service.UserFollowingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 17:54
 * @description 用户关注接口
 */
@Api(tags = {"用户关注接口"})
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
    @ApiOperation(value = "关注用户")
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
    @ApiOperation(value = "获取用户关注列表")
    public ResponseResult<List<FollowingGroupDO>> getUserFollowings() {
        Long userId = userSupport.getCurrentUserId();
        List<FollowingGroupDO> result = userFollowingService.listUserFollowings(userId);
        return new ResponseResult<>(result);
    }


    /**
     * 获取粉丝列表
     * @return 粉丝列表
     */
    @GetMapping("/user-fans")
    @ApiOperation(value = "获取粉丝列表")
    public ResponseResult<List<UserFollowingDO>> getUserFans() {
        Long userId = userSupport.getCurrentUserId();
        List<UserFollowingDO> result = userFollowingService.listUserFans(userId);
        return new ResponseResult<>(result);
    }

    /**
     * 添加用户关注分组
     * @param followingGroup 分组信息
     * @return 添加的分组ID
     */
    @PostMapping("/user-following-groups")
    @ApiOperation(value = "添加用户关注分组")
    public ResponseResult<Long> saveUserFollowingGroups(@RequestBody FollowingGroupDO followingGroup) {
        Long userId = userSupport.getCurrentUserId();
        followingGroup.setUserId(userId);

        Long groupId = followingGroupService.saveUserFollowingGroups(followingGroup);
        return new ResponseResult<>(groupId);
    }

    /**
     * 查询用户关注分组
     * @return 用户分组信息
     */
    @GetMapping("/user-following-groups")
    @ApiOperation(value = "查询用户关注分组")
    public ResponseResult<List<FollowingGroupDO>> getUserFollowingGroups() {
        Long userId = userSupport.getCurrentUserId();
        List<FollowingGroupDO> list = followingGroupService.listUserFollowingGroups(userId);
        return new ResponseResult<>(list);
    }

}
