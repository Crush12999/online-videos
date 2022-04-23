package com.ataraxia.service;

import com.ataraxia.domain.auth.UserRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chuchen
* @description 针对表【t_user_role(用户角色关联表)】的数据库操作Service
* @createDate 2022-04-23 14:22:03
*/
public interface UserRoleService extends IService<UserRoleDO> {

    /**
     * 查询用户角色列表
     * @param userId 用户ID
     * @return 用户相关角色列表
     */
    List<UserRoleDO> listUserRoleByUserId(Long userId);
}
