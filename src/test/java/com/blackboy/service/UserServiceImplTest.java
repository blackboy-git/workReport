package com.blackboy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blackboy.domain.User;
import com.blackboy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void testSaveUser(){
        User user = new User();
        user.setUserId("wangwu2");
        user.setPassword("123465");
        user.setUserName("王五");
        user.setRole("user");
        userService.save(user);
    }

    @Test
    void testGetPage(){
        IPage page = userService.getPage(2, 2);
        System.out.println("pageCurrent===>"+ page.getCurrent());
        System.out.println("pageSize===>"+ page.getSize());
        System.out.println("pageTotal===>"+ page.getTotal());
        System.out.println("pageRecords===>"+ page.getRecords());
        System.out.println("pagePages===>"+ page.getPages());
    }

    @Test
    void testLogin(){
        boolean login = userService.userIsExist("admin", "1");
        System.out.println("login===>"+ login);
    }

    @Test
    void testLogin2(){
        User user = userService.userLogin("byb", "222");
        System.out.println(user);
    }

    @Test
    void testRestPassword(){
        boolean result = userService.resetUserPassword("byb", "222", "333");
        System.out.println("result===>"+ result);
    }

}
