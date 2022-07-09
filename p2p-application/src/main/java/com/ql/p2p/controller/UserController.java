package com.ql.p2p.controller;

import com.ql.p2p.application.UserService;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:48
 */
@RestController("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询用户信息
     *
     * @param userDto the userDto
     * @return the {@link Result<UserDto>} data
     */
    @PostMapping("/queryUserInfo")
    public Result<UserDto> queryUserInfo(@RequestBody UserDto userDto) {
        return userService.queryUserInfo(userDto);
    }
}
