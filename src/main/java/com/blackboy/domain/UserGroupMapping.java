package com.blackboy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserGroupMapping {
    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("group_id")
    private Integer groupId;

    @TableField("member_order")
    private Integer memberOrder;
}
