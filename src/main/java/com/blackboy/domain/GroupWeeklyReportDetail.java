package com.blackboy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class GroupWeeklyReportDetail {

    //分组周报的id
    private Integer groupWeeklyReportId;

    //周报名称
    private String reportName;

    //分组名称
    private String groupName;

    //成员周报
    private List<WeeklyReport> membersWeeklyReports;

}
