package com.ql.p2p.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:59
 */
@Data
public class BaseDto {
    private Long id;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;
}
