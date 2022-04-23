package com.ataraxia.mapper.auth;

import com.ataraxia.domain.auth.AuthRoleMenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role_menu(权限控制--角色页面菜单关联表)】的数据库操作Mapper
* @createDate 2022-04-23 13:39:13
* @Entity generator.domain.AuthRoleMenu
*/
public interface AuthRoleMenuMapper extends BaseMapper<AuthRoleMenuDO> {

    /**
     * 通过角色ID集合查所具有的页面菜单权限
     * @param roleIdSet 角色ID集合
     * @return 相关菜单权限列表
     */
    List<AuthRoleMenuDO> listAuthRoleMenusByRoleIds(@Param("roleIdSet") Set<Long> roleIdSet);
}




