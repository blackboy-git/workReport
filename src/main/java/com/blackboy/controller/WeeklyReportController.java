package com.blackboy.controller;

import com.blackboy.controller.util.Result;
import com.blackboy.domain.Statistics;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/workReport")
public class WeeklyReportController {

    @Autowired
    private WeeklyReportService weeklyReportService;

    //获取用户的全部周报
    @GetMapping("/{userId}")
    public Result getWeeklyReport(@PathVariable String userId) {
        Collection<WeeklyReport> reports = weeklyReportService.getByUserId(userId);
        if (reports.isEmpty()){
            return new Result(false,"该用户暂无周报");
        }
        return new Result(true, reports);
    }

    //更新用户周报
    @PutMapping
    public Result updateWeeklyReport(@RequestBody WeeklyReport report) {
        try {
            System.out.println("收到的report:" + report.toString());
            report.setUpdateTime(LocalDateTime.now());
            report.setStatus(WeeklyReport.Status.valueOf("SUBMITTED"));
            if (weeklyReportService.updateById(report)) {
                return new Result(true, "更新成功");
            } else {
                return new Result(false, "更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新过程中出现异常：" + e.getMessage());
        }
    }

    //删除用户周报
    @DeleteMapping("/{reportId}")
    public Result deleteWeeklyReport(@PathVariable Integer reportId) {
        try {
            if (weeklyReportService.delete(reportId)) {
                return new Result(true, "删除成功");
            } else {
                return new Result(false, "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除过程中出现异常：" + e.getMessage());
        }
    }

    //获取用户上周的周报数据
    @GetMapping("/lastWeek/{userId}")
    public Result getLastWeeklyReport(@PathVariable String userId) {
        Collection<WeeklyReport> reports = weeklyReportService.getRecentWeeklyReport(userId,2);
        //先判断reports中是否有2条数据，如果有才返回第二条数据，如果没有则说明上周没有填写过数据
        if (reports.size() == 2){
            return new Result(true, reports.toArray()[1]);
        }else{
            return new Result(false, "上周没有填写过周报");
        }
    }

    //获取用户所在组的全部成员的周报
    @GetMapping("/groupReports/{userId}")
    public Result getGroupWeeklyReports(@PathVariable String userId) {
        Collection<WeeklyReport> reports = weeklyReportService.getGroupWeeklyReports(userId);
        if (reports.isEmpty()){
            return new Result(false,"该用户组暂无周报");
        }
        return new Result(true, reports);
    }

    //获取用户所在组的全部成员
    @GetMapping("/groupMembers/{userId}")
    public Result getGroupMemberByUserId(@PathVariable String userId) {
        Collection<Map<String, Object>> reports = weeklyReportService.getGroupMemberByUserId(userId);
        if (reports.isEmpty()){
            return new Result(false,"该用户暂无周报");
        }
        return new Result(true, reports);
    }

    //获取用户最近4次周报的信息
    @GetMapping("/recent/{userId}")
    public Result getRecentWeeklyReport(@PathVariable String userId) {
        Collection<WeeklyReport> reports = weeklyReportService.getRecentWeeklyReport(userId,4);
        if (reports.isEmpty()){
            return new Result(false,"该用户暂无周报");
        }
        return new Result(true, reports);
    }

    //统计用户使用周报系统的数据
    @GetMapping("/statistics/{userId}")
    public Result getStatistics(@PathVariable String userId) {
        Statistics statistics = weeklyReportService.getStatistics(userId);
        if (statistics == null){
            return new Result(false,"该用户暂无周报");
        }else{
            return new Result(true, statistics);
        }

    }
}
