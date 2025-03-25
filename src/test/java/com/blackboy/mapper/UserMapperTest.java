package com.blackboy.mapper;

import com.blackboy.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetUserByUserid() {
        User user = userMapper.selectByUserId("byb");
        System.out.println(user);
    }
}
