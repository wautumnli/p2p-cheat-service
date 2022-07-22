package com.ql.p2p.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:28
 */
@Data
@TableName("p2p_auth")
public class AuthPo {
    @TableField(value = "username")
    private String username;
    @TableField(value = "app_type")
    private String appType;
    @TableField(value = "open_id")
    private Long openId;
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableLogic
    private Integer deleted;
    @Version
    @TableField(value = "ver")
    private Integer ver;
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
