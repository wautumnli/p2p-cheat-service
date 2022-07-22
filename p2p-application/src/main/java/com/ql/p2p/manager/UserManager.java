package com.ql.p2p.manager;

import com.ql.p2p.domain.Auth;
import com.ql.p2p.domain.User;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:51
 */
public interface UserManager {
    /**
     * function is save
     *
     * @param user the user
     * @param auth the auth
     */
    void save(User user, Auth auth);
}
