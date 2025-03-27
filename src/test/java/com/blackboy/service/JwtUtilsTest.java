package com.blackboy.service;

import com.blackboy.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void testMockNewSecret() throws NoSuchAlgorithmException {
        System.out.println(jwtUtils.mockNewSecret());
    }

    @Test
    void testGenerateToken() {
        String token = jwtUtils.generateToken("blackboy");
        System.out.println(token);
    }

    @Test
    //验证token
    void testValidateToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibGFja2JveSIsImlhdCI6MTc0MzA2NjI1NCwiZXhwIjoxNzQzMDY5ODU0fQ.dG-KEzV-oHe_VrgT3tmXmj9_65vRr9745qHpgLHiI1o";
        boolean result = jwtUtils.validateToken(token);
        System.out.println(result);
    }

    @Test
    //测试获取用户信息
    void testGetUsernameFromToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibGFja2JveSIsImlhdCI6MTc0MzA2NjI1NCwiZXhwIjoxNzQzMDY5ODU0fQ.dG-KEzV-oHe_VrgT3tmXmj9_65vRr9745qHpgLHiI1o";
        String username = jwtUtils.getUsernameFromToken(token);
        System.out.println(username);
    }
}
