package com.ql.p2p.controller;

import com.ql.p2p.application.UserService;
import com.ql.p2p.dto.AuthDto;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/10 01:28
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @Resource
    private UserService userService;

    /**
     * 注册
     *
     * @param userDto the userDto
     * @return the {@link Result<String>} data
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    /**
     * function is login
     *
     * @param userDto the userDto
     * @return the {@link Result<AuthDto>} data
     */
    @PostMapping("/login")
    public Result<AuthDto> login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
