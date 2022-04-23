package com.ataraxia.service.impl;

import com.ataraxia.domain.auth.AuthRoleDO;
import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.ataraxia.domain.auth.AuthRoleMenuDO;
import com.ataraxia.mapper.auth.AuthRoleMapper;
import com.ataraxia.service.AuthRoleElementOperationService;
import com.ataraxia.service.AuthRoleMenuService;
import com.ataraxia.service.AuthRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role(权限控制--角色表)】的数据库操作Service实现
* @createDate 2022-04-23 14:22:03
*/
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRoleDO>
    implements AuthRoleService {

    @Autowired
    private AuthRoleElementOperationService authRoleElementOperationService;

    @Autowired
    private AuthRoleMenuService authRoleMenuService;

    /**
     * 通过角色ID集合查所具有的页面元素操作权限
     * @param roleIdSet 角色ID集合
     * @return 相关页面元素操作权限列表
     */
    @Override
    public List<AuthRoleElementOperationDO> listRoleElementOperationsByRoleIds(Set<Long> roleIdSet) {
        return authRoleElementOperationService.listRoleElementOperationsByRoleIds(roleIdSet);
    }

    /**
     * 通过角色ID集合查所具有的页面菜单权限
     * @param roleIdSet 角色ID集合
     * @return 相关菜单权限列表
     */
    @Override
    public List<AuthRoleMenuDO> listAuthRoleMenusByRoleIds(Set<Long> roleIdSet) {
        return authRoleMenuService.listAuthRoleMenusByRoleIds(roleIdSet);
    }
}




