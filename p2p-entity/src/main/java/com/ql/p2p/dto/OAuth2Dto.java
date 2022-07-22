package com.ql.p2p.dto;

import lombok.Data;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:31
 */
@Data
public class OAuth2Dto {

    private boolean newUser;
    private TokenDto token;

    public static OAuth2Dto notRegister() {
        OAuth2Dto oAuth2Dto = new OAuth2Dto();
        oAuth2Dto.setNewUser(true);
        return oAuth2Dto;
    }

    public OAuth2Dto fill(TokenDto tokenDto) {
        setToken(tokenDto);
        return this;
    }
}
