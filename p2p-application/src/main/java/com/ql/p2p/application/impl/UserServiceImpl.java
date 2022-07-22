package com.ql.p2p.application.impl;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.gimpy.FishEyeGimpyRenderer;
import cn.hutool.core.util.IdUtil;
import com.ql.p2p.application.IAuthService;
import com.ql.p2p.application.UserService;
import com.ql.p2p.domain.Auth;
import com.ql.p2p.domain.User;
import com.ql.p2p.dto.*;
import com.ql.p2p.enums.Oauth2Enums;
import com.ql.p2p.exception.P2pException;
import com.ql.p2p.manager.UserManager;
import com.ql.p2p.repository.AuthRepository;
import com.ql.p2p.repository.UserRepository;
import com.ql.p2p.util.CaptchaUtils;
import com.ql.p2p.util.Result;
import com.ql.p2p.utils.JwtProperties;
import com.ql.p2p.utils.JwtTokenUtils;
import com.ql.p2p.utils.RedisUtils;
import com.ql.p2p.validtion.UserValid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private IAuthService<GithubUser> githubAuthService;
    @Resource
    private AuthRepository authRepository;
    @Resource
    private UserManager userManager;

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
    public Result<TokenDto> login(UserDto userDto) {
        // 校验
        UserValid.login(userDto);
        // 验证码校验
        validationCaptcha(userDto.getUuid(), userDto.getCaptcha());
        // 查询用户信息
        UserDto existUserDto = userRepository.queryUserInfo(userDto);
        if (existUserDto == null) {
            throw P2pException.fail("用户{0}不存在", userDto.getUsername());
        }
        if (!passwordEncoder.matches(userDto.getPassword(), existUserDto.getPassword())) {
            throw P2pException.fail("密码不正确,请检查");
        }
        // 创建token 返回
        return Result.success(createTokenDto(userDto.getUsername()));
    }

    @Override
    public Result<CaptchaDto> getCaptcha() {
        // 生成uuid 对应 验证码
        String uuid = IdUtil.simpleUUID();
        Captcha captcha = new Captcha.Builder(CaptchaUtils.CAPTCHA_W, CaptchaUtils.CAPTCHA_H)
                .addText().addBackground(new GradiatedBackgroundProducer())
                .gimp(new FishEyeGimpyRenderer())
                .build();
        // 保存对应关系，时间1分钟
        redisUtils.set(uuid, captcha.getAnswer(), CaptchaUtils.CAPTCHA_EXPIRE, TimeUnit.SECONDS);
        // 图片转成字节流返回
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(captcha.getImage(), "png", stream);
            return Result.success(CaptchaDto.success(uuid, stream.toByteArray()));
        } catch (IOException e) {
            return Result.fail(CaptchaDto.fail());
        }
    }

    @Override
    public Result<OAuth2Dto> githubAuth(String code) {
        // 获取github token
        String accessToken = githubAuthService.getAccessToken(code);
        // 获取用户信息
        GithubUser githubUser = githubAuthService.getUserInfo(accessToken);
        // 匹配当前系统是否存在用户
        AuthDto authDto = authRepository.queryAuth(Oauth2Enums.GITHUB.getCode(), githubUser.getId());
        // 存在直接返回token
        OAuth2Dto oAuth2Dto = new OAuth2Dto();
        if (authDto == null) {
            // 没有提示不存在，需填充用户信息
            User user = User.create(githubUser.getLogin());
            Auth auth = Auth.create(Oauth2Enums.GITHUB.getCode(), githubUser.getLogin(), githubUser.getId());
            userManager.save(user, auth);
            oAuth2Dto = OAuth2Dto.notRegister();
        }
        TokenDto tokenDto = createTokenDto(githubUser.getLogin());
        return Result.success(oAuth2Dto.fill(tokenDto));
    }

    private void validationCaptcha(String uuid, String captcha) {
        // 拉取验证码，
        String loadCaptcha = redisUtils.getStr(uuid);
        // 校验是否正确
        if (!Objects.equals(loadCaptcha, captcha)) {
            throw P2pException.fail("验证码不正确或已过期");
        }
        // 删除验证码
        redisUtils.del(uuid);
    }

    /**
     * 创建token Dto
     *
     * @param username the username
     * @return the {@link TokenDto} data
     */
    private TokenDto createTokenDto(String username) {
        String token = jwtTokenUtils.generateToken(username);
        return new TokenDto()
                .setToken(token)
                .setTokenHead(jwtProperties.getTokenHead());
    }
}
