package com.blackboy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("password")
    private String password;

    @TableField("user_name")
    private String userName;

    @TableField("role")
    private Object role;

    @TableField("enabled")
    private boolean enabled = true;

    @TableField("avatar")
    private String avatar;
}
