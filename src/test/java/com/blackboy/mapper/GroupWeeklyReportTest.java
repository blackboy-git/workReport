package com.blackboy.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupWeeklyReportTest {

    @Autowired
    private GroupWeeklyReportMapper groupWeeklyReportMapper;

    @Test
    void testMemberWeeklyReportsByGroupReportId(){
        groupWeeklyReportMapper.selectMemberWeeklyReportsByGroupReportId(21);
    }
}
