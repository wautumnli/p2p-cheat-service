package com.ql.p2p.util;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:52
 */
public enum ResultEnums {

    /**
     * 请求状态枚举
     */
    SUCCESS(200, "request handle success"),
    ERROR(404, "request handle error"),
    ;

    private final int code;
    private final String msg;

    ResultEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
