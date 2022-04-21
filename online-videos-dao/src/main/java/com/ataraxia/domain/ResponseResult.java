package com.ataraxia.domain;

/**
 * @author Ataraxia
 * @create 2022/4/21 00:23
 * @description 通用数据返回对象
 */
public class ResponseResult<T> {

    private final static int SUCCESS_CODE = 0;
    
    private final static String SUCCESS_MESSAGE = "成功";

    private final static int FAILED_CODE = 1;

    private final static String FAILED_MESSAGE = "失败";

    private int code;

    private String msg;

    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(T data) {
        this.data = data;
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MESSAGE;
    }

    public static ResponseResult<String> success() {
        return new ResponseResult<>(null);
    }

    public static ResponseResult<String> success(String data) {
        return new ResponseResult<>(data);
    }

    public static ResponseResult<String> fail() {
        return new ResponseResult<>(FAILED_CODE, FAILED_MESSAGE);
    }

    public static ResponseResult<String> fail(Integer code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
