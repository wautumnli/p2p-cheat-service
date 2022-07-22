package com.ql.p2p.dto;

import lombok.Data;

/**
 * @author wanqiuli
 * @date 2022/7/22 20:40
 */
@Data
public class GithubUser {
    private String login;
    private Long id;
    private String nodeId;
}
