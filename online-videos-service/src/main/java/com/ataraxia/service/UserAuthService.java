package com.ataraxia.service;

import com.ataraxia.domain.UserAuthDTO;

/**
 * @author Ataraxia
 * @create 2022/4/23 13:37
 * @description 用户权限服务层接口
 */
public interface UserAuthService {

    /**
     * 获取用户权限
     * @param userId 用户ID
     * @return 权限列表
     */
    UserAuthDTO getUserAuthorities(Long userId);
}
