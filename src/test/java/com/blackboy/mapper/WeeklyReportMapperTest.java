package com.blackboy.mapper;

import com.blackboy.domain.WeeklyReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WeeklyReportMapperTest {

    @Autowired
    private WeeklyReportMapper weeklyReportMapper;

    @Test
    void testGetAllWeeklyReport() {
        List<WeeklyReport> weeklyReports = weeklyReportMapper.selectList(null);
        System.out.println(weeklyReports);
    }
}
