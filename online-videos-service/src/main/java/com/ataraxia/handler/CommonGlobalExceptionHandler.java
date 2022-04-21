package com.ataraxia.handler;

import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ataraxia
 * @create 2022/4/21 01:13
 * @description 全局异常处理
 * Ordered.HIGHEST_PRECEDENCE 最高的优先级
 */

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult<String> commonExceptionHandler(HttpServletRequest request, Exception e) {
        String errorMessage = e.getMessage();
        if (e instanceof ConditionException) {
            String errorCode = ((ConditionException) e).getCode();
            return new ResponseResult<>(errorCode, errorMessage);
        } else {
            return new ResponseResult<>("500", errorMessage);
        }
    }
}
