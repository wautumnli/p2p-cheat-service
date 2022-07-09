package com.ql.p2p.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanqiuli
 * @date 2022/7/9 21:20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = JwtProperties.PREFIX)
public class JwtProperties {
    public static final String PREFIX = "jwt";

    private String tokenHead;
    private String tokenHeader;
    private String secret;
    private Long expiration;
}
