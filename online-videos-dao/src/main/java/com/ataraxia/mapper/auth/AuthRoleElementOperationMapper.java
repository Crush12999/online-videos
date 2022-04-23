package com.ataraxia.mapper.auth;

import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role_element_operation(权限控制--角色与元素操作关联表)】的数据库操作Mapper
* @createDate 2022-04-23 13:39:13
* @Entity generator.domain.AuthRoleElementOperation
*/
public interface AuthRoleElementOperationMapper extends BaseMapper<AuthRoleElementOperationDO> {

    /**
     * 通过角色ID集合查所具有的页面元素操作权限
     * @param roleIdSet 角色ID集合
     * @return 相关页面元素操作权限列表
     */
    List<AuthRoleElementOperationDO> listRoleElementOperationsByRoleIds(@Param("roleIdSet") Set<Long> roleIdSet);
}




