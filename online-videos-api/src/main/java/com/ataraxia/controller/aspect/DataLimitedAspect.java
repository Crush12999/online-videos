package com.ataraxia.controller.aspect;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.UserMomentDO;
import com.ataraxia.domain.annotation.ApiLimitedRole;
import com.ataraxia.domain.annotation.DataLimited;
import com.ataraxia.domain.auth.UserRoleDO;
import com.ataraxia.domain.constant.AuthRoleConstant;
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
public class DataLimitedAspect {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserRoleService userRoleService;

    @Pointcut("@annotation(com.ataraxia.domain.annotation.DataLimited)")
    public void check() {}

    @Before("check()")
    public void doBefore(JoinPoint joinPoint) {
        Long userId = userSupport.getCurrentUserId();
        // 用户所属角色列表
        List<UserRoleDO> userRoleList = userRoleService.listUserRoleByUserId(userId);
        Set<String> roleCodeSet = userRoleList.stream().map(UserRoleDO::getRoleCode).collect(Collectors.toSet());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof UserMomentDO) {
                UserMomentDO userMoment = (UserMomentDO) arg;
                String type = userMoment.getType();
                // Lv1 用户只能发布类型为"0"的视频动态
                if (roleCodeSet.contains(AuthRoleConstant.ROLE_LV1) && !"0".equals(type)) {
                    throw new ConditionException("参数异常！");
                }
            }
        }

    }

}
