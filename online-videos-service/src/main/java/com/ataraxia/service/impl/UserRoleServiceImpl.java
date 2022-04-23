package com.ataraxia.service.impl;

import com.ataraxia.domain.auth.UserRoleDO;
import com.ataraxia.mapper.auth.UserRoleMapper;
import com.ataraxia.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chuchen
* @description 针对表【t_user_role(用户角色关联表)】的数据库操作Service实现
* @createDate 2022-04-23 14:22:03
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO>
    implements UserRoleService {

    /**
     * 查询用户角色列表
     * @param userId 用户ID
     * @return 用户相关角色列表
     */
    @Override
    public List<UserRoleDO> listUserRoleByUserId(Long userId) {
        return baseMapper.listUserRoleByUserId(userId);
    }
}




