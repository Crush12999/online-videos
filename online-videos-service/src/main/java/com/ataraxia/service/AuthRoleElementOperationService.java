package com.ataraxia.service;

import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role_element_operation(权限控制--角色与元素操作关联表)】的数据库操作Service
* @createDate 2022-04-23 14:22:03
*/
public interface AuthRoleElementOperationService extends IService<AuthRoleElementOperationDO> {

    /**
     * 通过角色ID集合查所具有的页面元素操作权限
     * @param roleIdSet 角色ID集合
     * @return 相关页面元素操作权限列表
     */
    List<AuthRoleElementOperationDO> listRoleElementOperationsByRoleIds(Set<Long> roleIdSet);
}
