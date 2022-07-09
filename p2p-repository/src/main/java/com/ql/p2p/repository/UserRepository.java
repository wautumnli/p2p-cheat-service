package com.ql.p2p.repository;

import com.ql.p2p.domain.User;
import com.ql.p2p.dto.UserDto;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:13
 */
public interface UserRepository {

    /**
     * function is queryUserInfo
     *
     * @param userQueryDto the userQueryDto
     * @return the {@link UserDto} data
     */
    UserDto queryUserInfo(UserDto userQueryDto);

    /**
     * function is getUserByUsername
     *
     * @param username the username
     * @return the {@link User} data
     */
    User getUserByUsername(String username);

    /**
     * function is save
     *
     * @param userDto the userDto
     */
    void save(UserDto userDto);
}
