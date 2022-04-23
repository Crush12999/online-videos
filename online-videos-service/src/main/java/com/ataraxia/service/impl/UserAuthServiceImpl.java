package com.ataraxia.service.impl;

import com.ataraxia.domain.UserAuthDTO;
import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.ataraxia.domain.auth.AuthRoleMenuDO;
import com.ataraxia.domain.auth.UserRoleDO;
import com.ataraxia.service.AuthRoleService;
import com.ataraxia.service.UserAuthService;
import com.ataraxia.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ataraxia
 * @create 2022/4/23 13:38
 * @description 用户权限服务层接口实现
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthRoleService authRoleService;

    /**
     * 获取用户权限
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public UserAuthDTO getUserAuthorities(Long userId) {
        // 获取用户角色列表
        List<UserRoleDO> userRoleList = userRoleService.listUserRoleByUserId(userId);

        // 把角色ID都查出来
        Set<Long> roleIdSet = userRoleList.stream().map(UserRoleDO::getRoleId).collect(Collectors.toSet());

        // 通过角色ID批量查询
        // 查询按钮操作权限和页面菜单权限
        List<AuthRoleElementOperationDO> roleElementOperationList = authRoleService.listRoleElementOperationsByRoleIds(roleIdSet);
        List<AuthRoleMenuDO> authRoleMenuList = authRoleService.listAuthRoleMenusByRoleIds(roleIdSet);

        // 构造返回参数
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setRoleElementOperationList(roleElementOperationList);
        userAuthDTO.setRoleMenuList(authRoleMenuList);

        return userAuthDTO;
    }

}
