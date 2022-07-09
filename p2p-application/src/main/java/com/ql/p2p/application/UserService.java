package com.ql.p2p.application;

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
}
