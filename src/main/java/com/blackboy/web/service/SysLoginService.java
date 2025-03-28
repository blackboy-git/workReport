package com.blackboy.web.service;

import com.blackboy.controller.util.Result;
import com.blackboy.domain.User;
import com.blackboy.service.UserService;
import com.blackboy.util.JwtUtils;
import com.blackboy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    public Result login(String username, String password) {
        // 登录前置校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new Result(false, "用户名或密码不能为空！");
        }

        User user = userService.userLogin(username, password);
        if (user == null) {
            return new Result(false, "用户名和密码错误！");
        }

        if (!user.isEnabled()) {
            return new Result(false, "用户已被禁用！");
        }

        user.setPassword("");
        String token = jwtUtils.generateToken(username);
        Result rs = new Result(true, "登录成功！");
        rs.setMsg("用户存在");
        rs.setData(user);
        rs.setToken(token);
        return rs;
    }

    public User getUserInfo(Integer userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return null;
        }
        user.setPassword("");
        return user;
    }
}
