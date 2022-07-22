package com.ql.p2p.domain;

import lombok.Data;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:55
 */
@Data
public class Auth {
    private String appType;
    private Long openId;
    private String username;
    private String createUser;
    private String updateUser;

    public static Auth create(String appType, String username, Long id) {
        Auth auth = new Auth();
        auth.setAppType(appType);
        auth.setUsername(username);
        auth.setOpenId(id);
        auth.setCreateUser(username);
        auth.setUpdateUser(username);
        return auth;
    }
}
