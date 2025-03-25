package com.blackboy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;

@Data
public class WeeklyReport{

    @TableId("id")
    private Integer id;               // 周报ID

    @TableField("user_id")
    private String userId;           // 用户ID

    @TableField("user_name")
    private String userName;        //用户姓名

    @TableField("report_name")
    private String reportName;      //周报名称

    @TableField("group_id")     //周报所属组ID
    private Integer groupId;

    @TableField("content")
    private String content;        // 周报内容

    @TableField("week_number")
    private Integer weekNumber;    // ISO周编号 (1-53)

    @TableField("year")
    private Integer year;          // 年份

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime; // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime updateTime; // 更新时间
    private Status status;         // 周报状态

    // 状态枚举定义
    public enum Status {
        DRAFT,      // 草稿状态
        SUBMITTED   // 已提交状态
    }

    // 可选构造器（用于创建新周报）
    public WeeklyReport() {}

    //
    public WeeklyReport(String userId, String userName,String reportName,Integer groupId, String content, LocalDateTime createTime) {
        this.userId = userId;
        this.userName = userName;
        this.reportName = reportName;
        this.groupId = groupId;
        this.content = content;
        // 计算并设置周数
        this.weekNumber = createTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        this.year = createTime.getYear();
        this.createTime = createTime;
        this.updateTime = createTime;   //默认新建的周报更新时间与创建时间一致

        this.status = Status.DRAFT; // 默认草稿状态
    }
}