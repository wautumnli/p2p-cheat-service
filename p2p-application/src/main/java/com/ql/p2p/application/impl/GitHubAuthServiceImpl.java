package com.ql.p2p.application.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ql.p2p.application.IAuthService;
import com.ql.p2p.dto.GithubUser;
import com.ql.p2p.exception.P2pException;
import com.ql.p2p.utils.Oauth2GitHubProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wanqiuli
 * @date 2022/7/22 20:39
 */
@Slf4j
@Service("githubAuthService")
public class GitHubAuthServiceImpl implements IAuthService<GithubUser> {
    @Resource
    private Oauth2GitHubProperties oauth2GitHubProperties;

    @Override
    public String getAccessToken(String code) {
        String url = oauth2GitHubProperties.getAccessTokenUrl() +
                "?client_id=" + oauth2GitHubProperties.getClientId() +
                "&client_secret=" + oauth2GitHubProperties.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";

        log.info("github三方登陆: {}", url);
        HttpResponse accept = HttpRequest.post(url)
                .header("accept", "application/json")
                .execute();
        if (!accept.isOk()) {
            throw new P2pException("github三方登陆失败");
        }
        String body = accept.body();
        log.info("github三方登陆结果: {}", body);
        JSONObject response = JSONUtil.parseObj(body);
        return (String) response.get("access_token");
    }

    @Override
    public String getOpenId(String accessToken) {
        return null;
    }

    @Override
    public String refreshToken(String code) {
        return null;
    }

    @Override
    public String getAuthorizationUrl() {
        return oauth2GitHubProperties.getAuthorizeUrl() +
                "?client_id=" + oauth2GitHubProperties.getClientId() +
                "&redirect_uri=" + oauth2GitHubProperties.getRedirectUrl();
    }

    @Override
    public GithubUser getUserInfo(String accessToken) {
        String userInfoUrl = oauth2GitHubProperties.getUserInfoUrl();
        HttpResponse execute = HttpRequest.get(userInfoUrl)
                .header("accept", "application/json")
                .header("Authorization", "token " + accessToken)
                .execute();
        if (!execute.isOk()) {
            throw new P2pException("没有获取到github用户信息");
        }
        String body = execute.body();
        log.info("github用户登陆信息{}", body);
        return JSONUtil.toBean(body, GithubUser.class);
    }
}
