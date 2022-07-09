package com.ql.p2p.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto{
    private String username;
}
