package com.ql.p2p.application.impl;

import com.ql.p2p.application.UserService;
import com.ql.p2p.dto.AuthDto;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.dao.UserDao;
import com.ql.p2p.exception.P2pException;
import com.ql.p2p.repository.UserRepository;
import com.ql.p2p.util.Result;
import com.ql.p2p.utils.JwtProperties;
import com.ql.p2p.utils.JwtTokenUtils;
import com.ql.p2p.validtion.UserValid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:07
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private JwtProperties jwtProperties;

    @Override
    public Result<UserDto> queryUserInfo(UserDto userQueryDto) {
        // 校验字段
        UserValid.queryUserInfo(userQueryDto);
        // 查询
        UserDto userResultDto = userRepository.queryUserInfo(userQueryDto);
        if (userResultDto == null) {
            throw P2pException.fail("{0}用户, 信息不存在", userQueryDto.getUsername());
        }
        return Result.success(userResultDto);
    }

    @Override
    public Result<String> register(UserDto userDto) {
        // 校验
        UserValid.register(userDto);
        // 查询用户信息
        UserDto existUserDto = userRepository.queryUserInfo(userDto);
        if (existUserDto != null) {
            throw P2pException.fail("用户{0}已存在,请更换用户名", userDto.getUsername());
        }
        // 加密
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setCreateUser(userDto.getUsername());
        userDto.setUpdateUser(userDto.getUsername());
        // 持久化
        userRepository.save(userDto);
        return Result.success("注册成功");
    }

    @Override
    public Result<AuthDto> login(UserDto userDto) {
        // 校验
        UserValid.login(userDto);
        // 查询用户信息
        UserDto existUserDto = userRepository.queryUserInfo(userDto);
        if (existUserDto == null) {
            throw P2pException.fail("用户{0}不存在", userDto.getUsername());
        }
        if (!passwordEncoder.matches(userDto.getPassword(), existUserDto.getPassword())) {
            throw P2pException.fail("密码不正确,请检查");
        }
        String token = jwtTokenUtils.generateToken(userDto.getUsername());
        AuthDto authDto = new AuthDto()
                .setToken(token)
                .setTokenHead(jwtProperties.getTokenHead());
        return Result.success(authDto);
    }
}
