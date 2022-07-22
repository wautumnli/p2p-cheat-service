package com.ql.p2p.enums;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:35
 */
public enum Oauth2Enums {

    /**
     * 三方认证类型
     */
    GITHUB("github"),
    ;

    private final String code;

    Oauth2Enums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
