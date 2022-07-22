package com.ql.p2p.manager.impl;

import com.ql.p2p.domain.Auth;
import com.ql.p2p.domain.User;
import com.ql.p2p.manager.UserManager;
import com.ql.p2p.repository.AuthRepository;
import com.ql.p2p.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserManagerImpl implements UserManager {

    @Resource
    private UserRepository userRepository;
    @Resource
    private AuthRepository authRepository;

    @Override
    public void save(User user, Auth auth) {
        userRepository.save(user);
        authRepository.save(auth);
    }
}
