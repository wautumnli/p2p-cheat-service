package com.ql.p2p.domain;

import lombok.Data;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:30
 */
@Data
public class User {

    private String username;
    private String password;
    private String nickname;
    private String createUser;
    private String updateUser;

    public static User create(String login) {
        User user = new User();
        user.setUsername(login);
        user.setNickname(login);
        user.setCreateUser(login);
        user.setUpdateUser(login);
        return user;
    }
}
