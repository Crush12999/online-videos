package com.ataraxia.service.impl;

import com.ataraxia.domain.auth.AuthRoleMenuDO;
import com.ataraxia.mapper.auth.AuthRoleMenuMapper;
import com.ataraxia.service.AuthRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role_menu(权限控制--角色页面菜单关联表)】的数据库操作Service实现
* @createDate 2022-04-23 14:22:03
*/
@Service
public class AuthRoleMenuServiceImpl extends ServiceImpl<AuthRoleMenuMapper, AuthRoleMenuDO>
    implements AuthRoleMenuService {

    /**
     * 通过角色ID集合查所具有的页面菜单权限
     * @param roleIdSet 角色ID集合
     * @return 相关菜单权限列表
     */
    @Override
    public List<AuthRoleMenuDO> listAuthRoleMenusByRoleIds(Set<Long> roleIdSet) {
        return baseMapper.listAuthRoleMenusByRoleIds(roleIdSet);
    }
}




