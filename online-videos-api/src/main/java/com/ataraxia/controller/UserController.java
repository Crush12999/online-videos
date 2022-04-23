package com.ataraxia.controller;

import com.alibaba.fastjson.JSONObject;
import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.PageResult;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.service.UserFollowingService;
import com.ataraxia.service.UserInfoService;
import com.ataraxia.service.UserService;
import com.ataraxia.util.RSAUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:32
 * @description 用户业务请求接口
 */
@Api(tags = {"用户业务请求接口"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserFollowingService userFollowingService;

    @Autowired
    private UserSupport userSupport;

    /**
     * 获取RSA公钥
     * @return RSA公钥字符串
     */
    @GetMapping("/rsa-pks")
    @ApiOperation(value = "获取RSA公钥")
    public ResponseResult<String> getRsaPublicKey() {
        String publicKey = RSAUtil.getPublicKeyStr();
        return new ResponseResult<>(publicKey);
    }

    /**
     * 添加用户
     * @param user 用户基本信息
     * @return 结果
     */
    @PostMapping("/users")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "password", value = "密码：经过RSA公钥加密的密文", required = true),
    })
    public ResponseResult<String> insertUser(@ApiIgnore @RequestBody UserDO user) {
        userService.insertUser(user);
        return ResponseResult.success();
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @GetMapping("/users")
    @ApiOperation(value = "获取用户信息")
    public ResponseResult<UserDO> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        UserDO user = userService.getUserInfo(userId);
        return new ResponseResult<>(user);
    }

    /**
     * 登录
     * @param user
     * @return token令牌
     */
    @PostMapping("/user-tokens")
    @ApiOperation(value = "登录")
    public ResponseResult<String> login(@RequestBody UserDO user) throws Exception {
        String token = userService.login(user);
        return ResponseResult.success(token);
    }

    /**
     * 更新用户信息
     * @param user 用户
     * @return 更新结果
     */
    @PutMapping("/users")
    @ApiOperation(value = "更新用户信息")
    public ResponseResult<String> updateUser(@RequestBody UserDO user) throws Exception {
        Long userId = userSupport.getCurrentUserId();
        user.setId(userId);
        user.setUpdateTime(new Date());
        userService.updateUser(user);
        return ResponseResult.success();
    }

    /**
     * 更新用户基本信息
     * @param userInfo 用户基本信息
     * @return 更新结果
     */
    @PutMapping("/user-infos")
    @ApiOperation(value = "更新用户基本信息")
    public ResponseResult<String> updateUserInfos(@RequestBody UserInfoDO userInfo) {
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        userInfoService.updateUserInfos(userInfo);
        return ResponseResult.success();
    }

    /**
     * 分页查询用户列表
     * @param no 当前页数
     * @param size 每页显示的条数
     * @param nick 昵称
     * @return 用户列表信息
     */
    @GetMapping("/user-infos")
    @ApiOperation(value = "分页查询用户列表")
    public ResponseResult<PageResult<UserInfoDO>> getPageListUserInfos(@RequestParam Integer no, @RequestParam Integer size, String nick) {
        Long userId = userSupport.getCurrentUserId();
        JSONObject params = new JSONObject();
        params.put("no", no);
        params.put("size", size);
        params.put("nick", nick);
        params.put("userId", userId);

        // 获得的初始分页结果
        PageResult<UserInfoDO> result = userService.getPageListUserInfos(params);

        // 需要校验一下列表中是否存在已关注过的用户，因为前端显示需要
        if (result.getTotal() > 0) {
            List<UserInfoDO> checkedUserInfosList = userFollowingService.listCheckFollowingStatus(result.getList(), userId);
            result.setList(checkedUserInfosList);
        }
        return new ResponseResult<>(result);
    }

}
