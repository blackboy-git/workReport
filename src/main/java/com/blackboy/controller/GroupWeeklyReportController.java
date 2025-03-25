package com.blackboy.controller;

import com.blackboy.domain.GroupWeeklyReportDetail;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.service.GroupWeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.blackboy.controller.util.Result;

import java.util.List;

@RestController
@RequestMapping("/groupWeeklyReport")
public class GroupWeeklyReportController {

    @Autowired
    private GroupWeeklyReportService groupWeeklyReportService;
    //获取全部分组的周报信息
    @GetMapping
    public Result getGroupWeeklyReports() {
        if (groupWeeklyReportService.list() != null){
            return new Result(true,"获取成功",groupWeeklyReportService.list());
        }else {
            return new Result(false,"获取失败");
        }
    }

    //获取周报的详情页面
    @GetMapping("/detail/{groupWeeklyReportId}")
    public Result getGroupWeeklyReportDetail(@PathVariable Integer groupWeeklyReportId) {
        List<WeeklyReport> groupWeeklyReportDetail = groupWeeklyReportService.getGroupWeeklyReportDetail(groupWeeklyReportId);
        if (groupWeeklyReportDetail != null){
            return new Result(true,"获取成功",groupWeeklyReportDetail);
        }else {
            return new Result(false,"获取失败");
        }
    }

    //更新周报的浏览次数
    @PutMapping("/updateViewCount")
    public Result updateViewCount(@RequestBody Integer reportId) {
        if (groupWeeklyReportService.updateCountById(reportId)){
            return new Result(true,"更新成功");
        }else {
            return new Result(false,"更新失败");
        }
    }
}
