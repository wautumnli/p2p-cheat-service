package com.ql.p2p.enums;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:52
 */
public enum ResultEnums {

    /**
     * 请求状态枚举
     */
    SUCCESS(200, "success"),
    ERROR(400, "fail"),
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
