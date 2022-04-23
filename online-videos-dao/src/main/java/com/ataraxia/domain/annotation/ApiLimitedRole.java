package com.ataraxia.domain.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Ataraxia
 * @create 2022/4/23 16:21
 * @description 针对无权限角色的接口权限注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Component
public @interface ApiLimitedRole {

    /**
     * 不被允许的角色的编码列表
     * @return 不允许的角色的编码列表
     */
    String[] limitedRoleCodeList() default {};
}
