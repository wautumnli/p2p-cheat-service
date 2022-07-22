package com.ql.p2p.repository;

import com.ql.p2p.domain.Auth;
import com.ql.p2p.dto.AuthDto;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:29
 */
public interface AuthRepository {

    /**
     * function is queryAuth
     *
     * @param type the type
     * @param id   the id
     * @return the {@link AuthDto} data
     */
    AuthDto queryAuth(String type, Long id);

    /**
     * function is save
     *
     * @param auth the auth
     */
    void save(Auth auth);
}
