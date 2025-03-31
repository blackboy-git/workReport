package com.blackboy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blackboy.domain.User;
import com.blackboy.domain.WeeklyReport;
import com.blackboy.service.impl.UserServiceImpl;
import com.blackboy.service.impl.WeeklyReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

@SpringBootTest
public class WeeklyReportServiceImplTest {

    @Autowired
    private WeeklyReportServiceImpl weeklyReportService;

    @Test
    void testSaveWorkReport(){
        for(int i = 0; i < 5; i++) {
            String content = "本周已完成工作\n" +
                    "1. 组织召开《新能源数据治理》软科学项目开题会；\n" +
                    "2. 组织召开《新能源数据治理》软科学项目开题会；\n" +
                    "3. 组织召开《新能源数据治理》软科学项目开题会；\n" +
                    "下周工作计划\n" +
                    "1. 继续完善《AAA项目》技术鉴定材料；\n" +
                    "3. 继续开发BinaC框架；";
            String creatTime = "2025-03-01 10:10:20";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            WeeklyReport report = new WeeklyReport("user2", "用户2","", 2, content, LocalDateTime.parse(creatTime, formatter),
                    2);

            weeklyReportService.save(report);
        }
    }

    @Test
    void testUpdateWorkReport(){
        WeeklyReport report = new WeeklyReport();
        report.setId(1);
        report.setContent("本周已完成工作\n" +
                "1. 组织召开《新能源数据治理》软科学项目开题会；\n" +
                "2. 完成《新能源数据治理》软科学项目合同申请、审批，取得合同编号；\n"
        );
        report.setUpdateTime(LocalDateTime.now());
        weeklyReportService.updateById(report);
    }

    @Test
    void testGetPage(){
        IPage page = weeklyReportService.getPage(2, 2);
        System.out.println("pageCurrent===>"+ page.getCurrent());
        System.out.println("pageSize===>"+ page.getSize());
        System.out.println("pageTotal===>"+ page.getTotal());
        System.out.println("pageRecords===>"+ page.getRecords());
        System.out.println("pagePages===>"+ page.getPages());
    }

    @Test
    void testGetGroupWeeklyReports(){
        Collection<WeeklyReport> reports = weeklyReportService.getGroupWeeklyReports("byb");
        System.out.println(reports);
    }

    @Test
    void testGetGroupMemberByUserId(){
        Collection<Map<String, Object>> reports = weeklyReportService.getGroupMemberByUserId("byb");
        System.out.println(reports);
    }

    //测试用户最近周报数据
    @Test
    void testGetRecentWeeklyReport(){
        Collection<WeeklyReport> reports = weeklyReportService.getRecentWeeklyReport("byb", 2);
        System.out.println(reports);
    }
}
