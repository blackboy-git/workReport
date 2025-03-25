package com.blackboy.service;

import com.blackboy.service.scheduler.WeeklyReportScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeeklyReportSchedulerTest {

    @Autowired
    private WeeklyReportScheduler weeklyReportScheduler;

    @Test
    void testGenerateWeeklyReports() {
        weeklyReportScheduler.generateWeeklyReports();
    }
}
