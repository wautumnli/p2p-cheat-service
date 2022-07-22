package com.ql.p2p.controller;

import com.ql.p2p.application.IAuthService;
import com.ql.p2p.application.UserService;
import com.ql.p2p.dto.*;
import com.ql.p2p.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/10 01:28
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;
    @Resource
    private IAuthService<GithubUser> githubUserIAuthService;

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
     * @return the {@link Result< TokenDto >} data
     */
    @PostMapping("/login")
    public Result<TokenDto> login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    /**
     * 获取验证码
     */
    @GetMapping("/getCaptcha")
    public Result<CaptchaDto> getCaptcha() {
        return userService.getCaptcha();
    }


    /**
     * 获取github三方认证地址
     *
     * @return the {@link Result<String>} data
     */
    @GetMapping("/getGithubUrl")
    public Result<String> getGithubUrl() {
        return Result.success(githubUserIAuthService.getAuthorizationUrl());
    }

    /**
     * Github回调认证
     *
     * @param code the code
     * @return the {@link Result<GithubUser>} data
     */
    @GetMapping("/callback")
    public Result<OAuth2Dto> callback(@RequestParam String code) {
        return userService.githubAuth(code);
    }
}
