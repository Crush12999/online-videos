package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserMomentDO;
import com.ataraxia.domain.annotation.ApiLimitedRole;
import com.ataraxia.domain.annotation.DataLimited;
import com.ataraxia.domain.constant.AuthRoleConstant;
import com.ataraxia.service.UserMomentService;
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
 * @create 2022/4/22 23:57
 * @description 用户动态接口
 */
@Api(tags = {"用户动态接口"})
@RestController
public class UserMomentController {

    @Autowired
    private UserMomentService userMomentService;

    @Autowired
    private UserSupport userSupport;

    /**
     * 用户发布动态
     * @param userMoment 用户动态
     * @return 结果
     * @throws Exception MQ处理异常
     */
    @ApiLimitedRole(limitedRoleCodeList = {AuthRoleConstant.ROLE_LV0})
    @DataLimited
    @PostMapping("/user-moments")
    @ApiOperation(value = "用户发布动态")
    public ResponseResult<String> insertUserMoments(@RequestBody UserMomentDO userMoment) throws Exception {
        Long userId = userSupport.getCurrentUserId();
        userMoment.setUserId(userId);

        userMomentService.insertUserMoments(userMoment);

        return ResponseResult.success();
    }

    /**
     * 查询用户关注的人的相关动态信息
     * @return 动态信息列表
     */
    @GetMapping("/user-subscribed-moments")
    @ApiOperation(value = "查询用户关注的人的相关动态信息")
    public ResponseResult<List<UserMomentDO>> getUserSubscribedMoments() {
        Long userId = userSupport.getCurrentUserId();
        List<UserMomentDO> list = userMomentService.listUserSubscribedMoments(userId);
        return new ResponseResult<>(list);
    }


}
