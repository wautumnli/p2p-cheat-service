package com.ql.p2p.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:30
 */
@Data
@TableName("p2p_user")
@EqualsAndHashCode(callSuper = true)
public class UserPo extends BasePo{

    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "nickname")
    private String nickname;
}
