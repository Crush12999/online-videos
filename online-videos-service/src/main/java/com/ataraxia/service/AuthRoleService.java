package com.ataraxia.service;

import com.ataraxia.domain.auth.AuthRoleDO;
import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.ataraxia.domain.auth.AuthRoleMenuDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role(权限控制--角色表)】的数据库操作Service
* @createDate 2022-04-23 14:22:03
*/
public interface AuthRoleService extends IService<AuthRoleDO> {

    /**
     * 通过角色ID集合查所具有的页面元素操作权限
     * @param roleIdSet 角色ID集合
     * @return 相关页面元素操作权限列表
     */
    List<AuthRoleElementOperationDO> listRoleElementOperationsByRoleIds(Set<Long> roleIdSet);

    /**
     * 通过角色ID集合查所具有的页面菜单权限
     * @param roleIdSet 角色ID集合
     * @return 相关菜单权限列表
     */
    List<AuthRoleMenuDO> listAuthRoleMenusByRoleIds(Set<Long> roleIdSet);

    /**
     * 通过 code 获取角色
     * @param roleCodeLv 角色等级
     * @return 对应角色
     */
    AuthRoleDO getRoleByCode(String roleCodeLv);
}
