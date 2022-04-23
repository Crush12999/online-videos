package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.UserAuthDTO;
import com.ataraxia.service.UserAuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ataraxia
 * @create 2022/4/23 13:36
 * @description 用户权限接口
 */
@Api(tags = {"用户权限接口"})
@RestController
public class UserAuthController {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/user-authorities")
    public ResponseResult<UserAuthDTO> getUserAuthorities() {
        Long userId = userSupport.getCurrentUserId();
        UserAuthDTO userAuthorities = userAuthService.getUserAuthorities(userId);
        return new ResponseResult<>(userAuthorities);
    }
}
