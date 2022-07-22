package com.ql.p2p.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wanqiuli
 * @date 2022/7/10 01:35
 */
@Data
@Accessors(chain = true)
public class TokenDto {
    private String token;
    private String tokenHead;
}
