package com.ataraxia.controller.aspect;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.annotation.ApiLimitedRole;
import com.ataraxia.domain.auth.UserRoleDO;
import com.ataraxia.domain.exception.ConditionException;
import com.ataraxia.service.UserRoleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ataraxia
 * @create 2022/4/23 16:24
 * @description 针对接口的权限控制切面
 */
@Order(1)
@Aspect
@Component
public class ApiLimitedRoleAspect {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserRoleService userRoleService;

    @Pointcut("@annotation(com.ataraxia.domain.annotation.ApiLimitedRole)")
    public void check() {}

    @Before("check() && @annotation(apiLimitedRole)")
    public void doBefore(JoinPoint joinPoint, ApiLimitedRole apiLimitedRole) {
        Long userId = userSupport.getCurrentUserId();
        // 用户所属角色列表
        List<UserRoleDO> userRoleList = userRoleService.listUserRoleByUserId(userId);
        // 获取希望限制的一些角色编码，就是希望哪些角色被限制
        String[] limitedRoleCodeList = apiLimitedRole.limitedRoleCodeList();

        Set<String> limitedRoleCodeSet = Arrays.stream(limitedRoleCodeList).collect(Collectors.toSet());
        Set<String> roleCodeSet = userRoleList.stream().map(UserRoleDO::getRoleCode).collect(Collectors.toSet());

        // 对两个列表取交集
        roleCodeSet.retainAll(limitedRoleCodeSet);
        // 取完交集之后，剩余的列表就是他们的交集。如果有交集，说明角色不具备权限
        if (roleCodeSet.size() > 0) {
            throw new ConditionException("权限不足！");
        }

    }

}
