package com.ql.p2p.validtion;

import com.ql.p2p.dto.UserDto;
import com.ql.p2p.util.AssertUtils;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:08
 */
public class UserValid {


    /**
     * 校验字段
     *
     * @param userDto the userDto
     */
    public static void queryUserInfo(UserDto userDto) {
        String checkField = "username";
        AssertUtils.check(userDto, checkField);
    }

    public static void register(UserDto userDto) {
        String checkField = "username,password,nickname";
        AssertUtils.check(userDto, checkField);
    }

    public static void login(UserDto userDto) {
        String checkField = "username,password";
        AssertUtils.check(userDto, checkField);
    }
}
