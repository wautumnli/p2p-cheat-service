package com.ql.p2p.application.impl;

import com.ql.p2p.application.UserService;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.dao.UserDao;
import com.ql.p2p.exception.P2pException;
import com.ql.p2p.repository.UserRepository;
import com.ql.p2p.util.Result;
import com.ql.p2p.validtion.UserValid;
import lombok.extern.slf4j.Slf4j;
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
}
