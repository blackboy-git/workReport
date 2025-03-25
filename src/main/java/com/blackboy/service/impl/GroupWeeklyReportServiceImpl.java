package com.blackboy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackboy.domain.GroupWeeklyReport;
import com.blackboy.domain.GroupWeeklyReportDetail;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.mapper.GroupWeeklyReportMapper;
import com.blackboy.service.GroupWeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupWeeklyReportServiceImpl extends ServiceImpl<GroupWeeklyReportMapper, GroupWeeklyReport> implements GroupWeeklyReportService {

    @Autowired
    private GroupWeeklyReportMapper groupWeeklyReportMapper;

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
        }
        return false;
    }

}
