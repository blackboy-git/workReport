package com.blackboy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackboy.domain.GroupWeeklyReport;
import com.blackboy.domain.GroupWeeklyReportDetail;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.mapper.GroupWeeklyReportMapper;
import com.blackboy.mapper.WeeklyReportMapper;
import com.blackboy.service.GroupWeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupWeeklyReportServiceImpl extends ServiceImpl<GroupWeeklyReportMapper, GroupWeeklyReport> implements GroupWeeklyReportService {

    @Autowired
    private GroupWeeklyReportMapper groupWeeklyReportMapper;

    @Autowired
    private WeeklyReportMapper weeklyReportMapper;

    //获取组内周报中的每个成员周报详情
    @Override
    public List<WeeklyReport> getGroupWeeklyReportDetail(Integer groupWeeklyReportId) {
        List<WeeklyReport> weeklyReports = groupWeeklyReportMapper.selectMemberWeeklyReportsByGroupReportId(groupWeeklyReportId);
        if(weeklyReports != null){
            return weeklyReports;
        }else{
            return null;
        }
    }

    //更新周报浏览次数
    @Override
    public boolean updateCountById(Integer reportId) {
        if(groupWeeklyReportMapper.updateCountById(reportId)){
            return true;
        }else{
            return false;
        }
    }

    // 删除某个组内周报
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGroupWeeklyReport(Integer groupWeeklyReportId) {
        try {
            // 先删除周报中所有成员的周报
            groupWeeklyReportMapper.deleteMemberWeeklyReportsByGroupReportId(groupWeeklyReportId);
            // 然后删除周报本身
            return groupWeeklyReportMapper.deleteById(groupWeeklyReportId) > 0;
        } catch (Exception e) {
            // 记录异常日志，这里可以使用日志框架，如 SLF4J
            System.err.println("删除组内周报时出现异常: " + e.getMessage());
            return false;
        }
    }

}
