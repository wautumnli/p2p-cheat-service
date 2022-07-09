package com.ql.p2p.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:31
 */
@Data
public class BasePo {
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
