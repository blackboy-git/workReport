package com.blackboy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackboy.domain.Statistics;
import com.blackboy.mapper.WeeklyReportMapper;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.service.WeeklyReportService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class WeeklyReportServiceImpl extends ServiceImpl<WeeklyReportMapper, WeeklyReport> implements WeeklyReportService {

    private final WeeklyReportMapper weeklyReportMapper;

    public WeeklyReportServiceImpl(WeeklyReportMapper weeklyReportMapper) {
        this.weeklyReportMapper = weeklyReportMapper;
    }

    @Override
    public IPage<WeeklyReport> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        return this.page(page);
    }

    @Override
    public IPage<WeeklyReport> getPage(Integer currentPage, Integer pageSize, WeeklyReport weeklyReport) {
        LambdaQueryWrapper<WeeklyReport> lq = new LambdaQueryWrapper<WeeklyReport>();
        lq.like(Strings.isNotEmpty(weeklyReport.getYear().toString()), WeeklyReport::getYear, weeklyReport.getYear());
        lq.like(Strings.isNotEmpty(weeklyReport.getUserId().toString()), WeeklyReport::getUserId, weeklyReport.getUserId());
        IPage page = new Page(currentPage, pageSize);
        return this.page(page,lq);
    }

    @Override
    public boolean delete(Integer id) {
        return weeklyReportMapper.deleteById(id) > 0;
    }

    @Override
    public Collection<WeeklyReport> getByUserId(String userId) {
        if (userId != null) {
            LambdaQueryWrapper<WeeklyReport> lq = new LambdaQueryWrapper<>();
            lq.eq(WeeklyReport::getUserId, userId)
                    .orderByDesc(WeeklyReport::getCreateTime);  //添加排序条件
            return weeklyReportMapper.selectList(lq);
        }
        return List.of();
    }

    //根据用户id获取组内所有成员的周报
    @Override
    public Collection<WeeklyReport> getGroupWeeklyReports(String userId) {
        if (userId != null) {
            LambdaQueryWrapper<WeeklyReport> lq = new LambdaQueryWrapper<>();
            lq.eq(WeeklyReport::getUserId, userId);
            return weeklyReportMapper.selectGroupWeeklyReports(userId);
        }
        return List.of();
    }

    @Override
    public Collection<Map<String, Object>> getGroupMemberByUserId(String userId) {
        if (userId != null) {
            LambdaQueryWrapper<WeeklyReport> lq = new LambdaQueryWrapper<>();
            lq.eq(WeeklyReport::getUserId, userId);
            return weeklyReportMapper.selectGroupMemberByUserId(userId);
        }
        return List.of();
    }

    //获取用户最近N次的周报数据
    @Override
    public Collection<WeeklyReport> getRecentWeeklyReport(String userId, Integer count) {

        if (userId != null) {
            LambdaQueryWrapper<WeeklyReport> lq = new LambdaQueryWrapper<>();
            lq.eq(WeeklyReport::getUserId, userId)
                    .orderByDesc(WeeklyReport::getCreateTime)
                    .last("limit " + count);
            return weeklyReportMapper.selectList(lq);
        }
        return List.of();
    }

    //统计用户使用周报的数据
    @Override
    public Statistics getStatistics(String userId) {
        //查找某个用户已经填写的周报数量,查询条件包括：用户id和周报状态为已提交的周报
        LambdaQueryWrapper<WeeklyReport> lq = new LambdaQueryWrapper<>();
        lq.eq(WeeklyReport::getUserId, userId);
        lq.eq(WeeklyReport::getStatus, "SUBMITTED");
        Long filledCount = weeklyReportMapper.selectCount(lq);

        //查找某个用户应该填写的周报数量，查询条件包括：用户id和周报状态为已提交、草稿的周报
        lq.clear();
        lq.eq(WeeklyReport::getUserId, userId);
        lq.in(WeeklyReport::getStatus, "SUBMITTED", "DRAFT");
        Long shouldFilledCount = weeklyReportMapper.selectCount(lq);

        Statistics statistics = new Statistics(filledCount,shouldFilledCount);
        return statistics;
    }
}
