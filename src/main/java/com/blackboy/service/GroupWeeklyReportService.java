package com.blackboy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackboy.domain.GroupWeeklyReport;
import com.blackboy.domain.GroupWeeklyReportDetail;
import com.blackboy.domain.WeeklyReport;

import java.util.List;

public interface GroupWeeklyReportService extends IService<GroupWeeklyReport>  {

    public List<WeeklyReport> getGroupWeeklyReportDetail(Integer groupWeeklyReportId);

    //更新周报浏览次数
    public boolean updateCountById(Integer reportId);

    //删除某个组内周报
    public boolean removeGroupWeeklyReport(Integer groupWeeklyReportId);
}
