package com.ql.p2p.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ql.p2p.dao.AuthDao;
import com.ql.p2p.domain.Auth;
import com.ql.p2p.dto.AuthDto;
import com.ql.p2p.po.AuthPo;
import com.ql.p2p.repository.AuthRepository;
import com.ql.p2p.transform.AuthMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:29
 */
@Repository
public class AuthRepositoryImpl implements AuthRepository {

    @Resource
    private AuthDao authDao;

    @Override
    public AuthDto queryAuth(String type, Long id) {
        AuthPo authPo = authDao.selectOne(Wrappers.<AuthPo>lambdaQuery()
                .eq(AuthPo::getAppType, type)
                .eq(AuthPo::getOpenId, id)
        );
        return AuthMapper.MAPPER.toDto(authPo);
    }

    @Override
    public void save(Auth auth) {
        AuthPo authPo = AuthMapper.MAPPER.toPo(auth);
        authDao.insert(authPo);
    }
}
