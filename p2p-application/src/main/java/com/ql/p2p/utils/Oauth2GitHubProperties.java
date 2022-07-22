package com.ql.p2p.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wanqiuli
 * @date 2022/7/22 20:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauth2.github")
public class Oauth2GitHubProperties {
    private String clientId;
    private String clientSecret;
    private String authorizeUrl;
    private String redirectUrl;
    private String accessTokenUrl;
    private String userInfoUrl;
}
