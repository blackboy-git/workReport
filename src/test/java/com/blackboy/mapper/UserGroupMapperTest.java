package com.blackboy.mapper;

import com.alibaba.fastjson.JSONArray;
import com.blackboy.domain.UserGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UserGroupMapperTest {

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Test
    void testCreateGroup(){
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("测试组3");
        userGroup.setReportPlanName("测试计划");
        userGroup.setStartDate(LocalDate.now().toString());
        userGroup.setCycleDays(1);
        userGroup.setAllowDays(1);
        userGroup.setActive(true);
        userGroupMapper.insert(userGroup);
    }
    @Test
    void testGetById(){
        UserGroup userGroup = userGroupMapper.selectById(2);
//        JSONArray array = JSONArray.parseArray(userGroup.getMemberOrder());
        System.out.println(userGroup);
    }

    @Test
    void testGetALL(){
        userGroupMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void testGetUsers( ){
        userGroupMapper.getAllUsers(2).forEach(System.out::println);
    }
}
