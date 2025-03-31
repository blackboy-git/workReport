package com.blackboy.service.scheduler;

import com.blackboy.domain.GroupWeeklyReport; // 假设存在这个实体类
import com.blackboy.domain.User;
import com.blackboy.domain.UserGroup;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.service.GroupWeeklyReportService; // 假设存在这个服务类
import com.blackboy.service.UserGroupService;
import com.blackboy.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeeklyReportScheduler {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private WeeklyReportService weeklyReportService;

    @Autowired
    private GroupWeeklyReportService groupWeeklyReportService; // 注入 GroupWeeklyReportService

    // 每天凌晨执行一次任务
    @Scheduled(cron = "0 0 0 * * ?")
    public void generateWeeklyReports() {
        // 生成周报分为两步骤，第一步，先生成 groupweeklyreport 表，第二步，生成 weeklyreport 表
        // 第一步，生成 groupweeklyreport 表
        List<UserGroup> activeGroups = userGroupService.getActiveGroups();
        for (UserGroup group : activeGroups) {
            LocalDate startDate = LocalDate.parse(group.getStartDate());
            int cycleDays = group.getCycleDays();
            LocalDate currentDate = LocalDate.now();
            LocalDateTime createTime = LocalDateTime.now();

            if (currentDate.isEqual(startDate) || (currentDate.isAfter(startDate) && (currentDate.toEpochDay() - startDate.toEpochDay()) % cycleDays == 0)) {
                // 生成 groupweeklyreport 表记录
                GroupWeeklyReport groupReport = new GroupWeeklyReport();
                groupReport.setGroupId(group.getId());
                groupReport.setGroupName(group.getGroupName());
                groupReport.setReportName(generateGroupReportName(startDate, cycleDays, group.getGroupName()));
                groupReport.setCreateTime(LocalDateTime.now());
                groupReport.setViewCount(0); // 初始化浏览次数为 0
                groupWeeklyReportService.save(groupReport);

                //生成weeklyReport表中用户的周报
                List<User> users = userGroupService.getAllUsers(group.getId());
                for (User user : users) {
                    // 构造周报名称，名称格式为：开始日期-结束日期-分组名称-用户姓名周报，如“2023/1/1-2023/1/7-分组1-用户1周报”
                    // 构造开始日期，格式为：YYYY/MM/DD
                    String reportName = generateUserReportName(startDate, cycleDays, group.getGroupName(), user.getUserName());
                    WeeklyReport report = new WeeklyReport(user.getUserId(),
                            user.getUserName(),
                            reportName,
                            group.getId(),
                            "",
                            createTime,
                            group.getAllowDays());
                    weeklyReportService.save(report);
                }
            }
        }
    }

    // 生成 groupweeklyreport 表的周报名称
    private String generateGroupReportName(LocalDate startDate, int cycleDays, String groupName) {
        String startDateStr = startDate.getYear() + "/" + startDate.getMonthValue() + "/" + startDate.getDayOfMonth();
        String endDateStr = startDate.plusDays(cycleDays - 1).getYear() + "/" + startDate.plusDays(cycleDays - 1).getMonthValue() + "/" + startDate.plusDays(cycleDays - 1).getDayOfMonth();
        return startDateStr + "-" + endDateStr + "-" + groupName + "周报";
    }

    //生成weekly_report表中用户的周报名称
    private String generateUserReportName(LocalDate startDate, int cycleDays,String groupName, String userName) {
        String startDateStr = startDate.getYear() + "/" + startDate.getMonthValue() + "/" + startDate.getDayOfMonth();
        String endDateStr = startDate.plusDays(cycleDays - 1).getYear() + "/" + startDate.plusDays(cycleDays - 1).getMonthValue() + "/" + startDate.plusDays(cycleDays - 1).getDayOfMonth();
        String other = groupName + "-" + userName + "-" + "周报";
        return startDateStr + "-" + endDateStr + "-" + other;
    }
}