package com.ql.p2p.util;

import java.text.MessageFormat;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:49
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(ResultEnums.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>(ResultEnums.ERROR);
        result.setData(data);
        return result;
    }

    public static Result<String> fail(String pattern, Object... param) {
        Result<String> result = new Result<>(ResultEnums.ERROR);
        result.setData(MessageFormat.format(pattern, param));
        return result;
    }

    public Result() {}

    public Result(ResultEnums resultEnum) {
        Result<T> result = new Result<T>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
