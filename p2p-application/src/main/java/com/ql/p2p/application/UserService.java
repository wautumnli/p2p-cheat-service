package com.ql.p2p.application;

import com.ql.p2p.dto.TokenDto;
import com.ql.p2p.dto.CaptchaDto;
import com.ql.p2p.dto.OAuth2Dto;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.util.Result;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:07
 */
public interface UserService {
    /**
     * function is queryUserInfo
     *
     * @param userDto the userDto
     * @return the {@link Result<UserDto>} data
     */
    Result<UserDto> queryUserInfo(UserDto userDto);

    /**
     * function is register
     *
     * @param userDto the userDto
     * @return the {@link Result<String>} data
     */
    Result<String> register(UserDto userDto);

    /**
     * function is login
     *
     * @param userDto the userDto
     * @return the {@link Result< TokenDto >} data
     */
    Result<TokenDto> login(UserDto userDto);

    /**
     * 返回图片验证码
     *
     * @return the {@link CaptchaDto} data
     */
    Result<CaptchaDto> getCaptcha();

    /**
     * function is githubAuth
     *
     * @param code the code
     * @return the {@link Result<OAuth2Dto>} data
     */
    Result<OAuth2Dto> githubAuth(String code);
}
