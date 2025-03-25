package com.blackboy.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupWeeklyReportTest {

    @Autowired
    private GroupWeeklyReportMapper groupWeeklyReportMapper;

    //测试组内周报的成员周报功能是否正常
    @Test
    void testMemberWeeklyReportsByGroupReportId(){
        groupWeeklyReportMapper.selectMemberWeeklyReportsByGroupReportId(21);
    }
}
