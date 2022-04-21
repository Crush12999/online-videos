package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.UserInfoDO;
import com.ataraxia.service.UserInfoService;
import com.ataraxia.service.UserService;
import com.ataraxia.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:32
 * @description 用户业务请求接口
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserSupport userSupport;

    /**
     * 获取RSA公钥
     * @return RSA公钥字符串
     */
    @GetMapping("/rsa-pks")
    public ResponseResult<String> getRsaPublicKey() {
        String publicKey = RSAUtil.getPublicKeyStr();
        return new ResponseResult<>(publicKey);
    }

    @GetMapping("/users")
    public ResponseResult<UserDO> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        UserDO user = userService.getUserInfo(userId);
        return new ResponseResult<>(user);
    }

    /**
     * 添加用户
     * @param user 用户基本信息
     * @return 结果
     */
    @PostMapping("/users")
    public ResponseResult<String> insertUser(@RequestBody UserDO user) {
        userService.insertUser(user);
        return ResponseResult.success();
    }

    /**
     * 登录
     * @param user
     * @return token令牌
     */
    @PostMapping("/user-tokens")
    public ResponseResult<String> login(@RequestBody UserDO user) throws Exception {
        String token = userService.login(user);
        return ResponseResult.success(token);
    }

    @PutMapping("/users")
    public ResponseResult<String> updateUser(@RequestBody UserDO user) throws Exception {
        Long userId = userSupport.getCurrentUserId();
        user.setId(userId);
        userService.updateUser(user);
        return ResponseResult.success();
    }

    @PutMapping("/user-infos")
    public ResponseResult<String> updateUserInfos(@RequestBody UserInfoDO userInfo) {
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        userInfoService.updateUserInfos(userInfo);
        return ResponseResult.success();
    }

}
