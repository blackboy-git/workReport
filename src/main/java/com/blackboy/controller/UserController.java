package com.blackboy.controller;

import com.blackboy.controller.util.ResetPasswordRequest;
import com.blackboy.controller.util.Result;
import com.blackboy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.blackboy.domain.User;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Result getUsers(){
        return new Result(true,userService.list());
    }

    @GetMapping("/user/list")
    public Result getUserList(){
        return new Result(true,userService.list());
    }

    @PostMapping("/user/add")
    public Result addUser(@RequestBody User user){
        userService.addUser(user);
        return new Result(true,"添加成功");
    }

    @PutMapping("/user")
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new Result(true,"修改成功");
    }

    @DeleteMapping("/user/{userId}")
    public Result deleteUser(@PathVariable String userId){
        userService.deleteByUserId(userId);
        return new Result(true,"删除成功");
    }

    //重置密码
    @PutMapping("/user/resetPassword")
    public Result resetUserPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        boolean success = userService.resetUserPassword(resetPasswordRequest.getUserId(),resetPasswordRequest.getOldPassword(),
                resetPasswordRequest.getNewPassword());
        if (!success){
            return new Result(false,"重置密码失败");
        }else{
            return new Result(true,"重置密码成功");
        }
    }

    //修改用户状态
    @GetMapping("/user/setStatus/{userId}")
    public Result setStatus(@PathVariable String userId) {
        try {
            // 根据 userId 获取用户信息
            User user = userService.getUserByUserId(userId);
            if (user == null) {
                return new Result(false, "用户不存在");
            }

            // 切换用户的启用状态
            boolean newStatus = !user.isEnabled();
            user.setEnabled(newStatus);

            // 更新用户信息到数据库
            if (userService.updateById(user)) {
                String message = newStatus ? "启用成功" : "禁用成功";
                return new Result(true, message);
            } else {
                String message = newStatus ? "启用失败" : "禁用失败";
                return new Result(false, message);
            }
        } catch (Exception e) {
            // 处理异常，返回错误信息
            return new Result(false, "操作失败：" + e.getMessage());
        }
    }

}
