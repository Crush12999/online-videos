package com.ataraxia.mapper.auth;

import com.ataraxia.domain.auth.UserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author chuchen
* @description 针对表【t_user_role(用户角色关联表)】的数据库操作Mapper
* @createDate 2022-04-23 13:39:13
* @Entity generator.domain.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 查询用户的角色列表信息
     * @param userId 用户ID
     * @return 角色列表信息
     */
    List<UserRoleDO> listUserRoleByUserId(Long userId);
}




