package com.blackboy.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blackboy.domain.User;
import com.blackboy.domain.UserGroup;
import com.blackboy.service.impl.UserGroupServiceImpl;
import com.blackboy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class UserGroupServiceImplTest {

    @Autowired
    private UserGroupServiceImpl userGroupService;

    @Test
    void testSaveUserGroup(){
        UserGroup group = new UserGroup();
        group.setGroupName("二进制项目组");
        // 创建字符串数组
//        String[] users = {"byb", "user2"};
//
//        // 将字符串数组转换为JSONArray
//        JSONArray jsonArray = new JSONArray(Arrays.asList(users));
//
//        // 将JSONArray转换为JSON格式的字符串
//        String jsonString = jsonArray.toJSONString();
//        group.setMemberOrder(jsonString);
        userGroupService.save(group);
    }

    @Test
    void testGetPage(){
        IPage page = userGroupService.getPage(2, 2);
        System.out.println("pageCurrent===>"+ page.getCurrent());
        System.out.println("pageSize===>"+ page.getSize());
        System.out.println("pageTotal===>"+ page.getTotal());
        System.out.println("pageRecords===>"+ page.getRecords());
        System.out.println("pagePages===>"+ page.getPages());
    }

}
