package com.ataraxia.service.impl;

import com.ataraxia.domain.auth.AuthRoleElementOperationDO;
import com.ataraxia.mapper.auth.AuthRoleElementOperationMapper;
import com.ataraxia.service.AuthRoleElementOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* @author chuchen
* @description 针对表【t_auth_role_element_operation(权限控制--角色与元素操作关联表)】的数据库操作Service实现
* @createDate 2022-04-23 14:22:03
*/
@Service
public class AuthRoleElementOperationServiceImpl extends ServiceImpl<AuthRoleElementOperationMapper, AuthRoleElementOperationDO>
    implements AuthRoleElementOperationService {

    /**
     * 通过角色ID集合查所具有的页面元素操作权限
     * @param roleIdSet 角色ID集合
     * @return 相关页面元素操作权限列表
     */
    @Override
    public List<AuthRoleElementOperationDO> listRoleElementOperationsByRoleIds(Set<Long> roleIdSet) {
        return baseMapper.listRoleElementOperationsByRoleIds(roleIdSet);
    }
}




