package com.blackboy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupWeeklyReport {
    @TableId("id")
    private Integer id;

    @TableField("group_id")
    private Integer groupId;

    @TableField("group_name")
    private String groupName;

    @TableField("report_name")
    private String reportName;

    @TableField("view_count")
    private Integer viewCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @TableField("create_time")
    private LocalDateTime createTime;

}
