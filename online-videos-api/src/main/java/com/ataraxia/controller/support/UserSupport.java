package com.ataraxia.controller.support;

import com.ataraxia.domain.UserDO;
import com.ataraxia.domain.exception.ConditionException;
import com.ataraxia.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Ataraxia
 * @create 2022/4/21 13:50
 * @description 用户相关支撑的功能
 */
@Component
public class UserSupport {

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 获取当前用户ID
     * @return 当前用户ID
     */
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 从请求头中获取 Token
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = tokenUtil.verifyToken(token);
        if (userId < 0) {
            throw new ConditionException("非法用户！");
        }
        return userId;
    }

}
