package com.ql.p2p.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ql.p2p.dao.UserDao;
import com.ql.p2p.domain.User;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.po.UserPo;
import com.ql.p2p.repository.UserRepository;
import com.ql.p2p.transform.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:13
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserDao userDao;

    @Override
    public UserDto queryUserInfo(UserDto userQueryDto) {
        UserPo userPo = userDao.selectOne(Wrappers.<UserPo>lambdaQuery()
                .eq(UserPo::getUsername, userQueryDto.getUsername()));
        return UserMapper.MAPPER.toDto(userPo);
    }

    @Override
    public User getUserByUsername(String username) {
        UserPo userPo = userDao.selectOne(Wrappers.<UserPo>lambdaQuery()
                .eq(UserPo::getUsername, username));
        return UserMapper.MAPPER.toEntity(userPo);
    }

    @Override
    public void save(UserDto userDto) {
        UserPo userPo = UserMapper.MAPPER.toPo(userDto);
        userDao.insert(userPo);
    }
}
