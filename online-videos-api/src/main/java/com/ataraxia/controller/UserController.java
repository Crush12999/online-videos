package com.ataraxia.controller;

import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserDO;
import com.ataraxia.service.UserService;
import com.ataraxia.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ataraxia
 * @create 2022/4/21 10:32
 * @description 用户业务请求接口
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取RSA公钥
     * @return RSA公钥字符串
     */
    @GetMapping("/rsa-pks")
    public ResponseResult<String> getRsaPublicKey() {
        String publicKey = RSAUtil.getPublicKeyStr();
        return ResponseResult.success(publicKey);
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


}
