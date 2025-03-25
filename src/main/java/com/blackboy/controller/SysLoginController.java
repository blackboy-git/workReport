package com.blackboy.controller;

import com.blackboy.domain.LoginBody;
import com.blackboy.web.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blackboy.controller.util.Result;

@RestController
@RequestMapping("/api")
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
        System.out.println(loginBody);
        return sysLoginService.login(loginBody.getUserId(),loginBody.getPassword());
    }

}
