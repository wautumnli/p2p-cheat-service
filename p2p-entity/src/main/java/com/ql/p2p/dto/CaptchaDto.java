package com.ql.p2p.dto;

import lombok.Data;

/**
 * @author wanqiuli
 * @date 2022/7/21 22:37
 */
@Data
public class CaptchaDto {
    private byte[] image;
    private String uuid;
    private boolean suc;

    public static CaptchaDto fail() {
        CaptchaDto captchaDto = new CaptchaDto();
        captchaDto.setSuc(false);
        return captchaDto;
    }

    public static CaptchaDto success(String uuid, byte[] toByteArray) {
        CaptchaDto captchaDto = new CaptchaDto();
        captchaDto.setUuid(uuid);
        captchaDto.setImage(toByteArray);
        captchaDto.setSuc(true);
        return captchaDto;
    }
}
