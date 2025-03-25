package com.blackboy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackboy.domain.Statistics;
import com.blackboy.domain.WeeklyReport;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface WeeklyReportService extends IService<WeeklyReport> {
    IPage<WeeklyReport> getPage(int currentPage, int pageSize);

    IPage<WeeklyReport> getPage(Integer currentPage, Integer pageSize, WeeklyReport report);

    boolean delete(Integer id);

    // 根据用户id获取周报
    Collection<WeeklyReport> getByUserId(String userid);

    // 根据用户id获取组内周报
    Collection<WeeklyReport> getGroupWeeklyReports(String userId);

    // 根据用户id获取组内成员名单
    Collection<Map<String, Object>> getGroupMemberByUserId(String userId);

    //获取用户最近N次的周报数据
    Collection<WeeklyReport> getRecentWeeklyReport(String userId,Integer count);

    //统计用户使用周报的数据
    Statistics getStatistics(String userId);

}
