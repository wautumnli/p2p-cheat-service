package com.ql.p2p.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ql.p2p.dto.UserDto;
import com.ql.p2p.po.UserPo;
import org.springframework.stereotype.Repository;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:06
 */
public interface UserDao extends BaseMapper<UserPo> {

    UserDto queryUserInfo(UserDto userQueryDto);
}
