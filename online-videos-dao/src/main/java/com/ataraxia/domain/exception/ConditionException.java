package com.ataraxia.domain.exception;

/**
 * @author Ataraxia
 * @create 2022/4/21 01:16
 * @description 条件异常
 */
public class ConditionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    public ConditionException(Integer code, String name) {
        super(name);
        this.code = code;
    }

    public ConditionException(String name) {
        super(name);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
