package com.blackboy.controller;

import com.blackboy.controller.util.AddUsersRequest;
import com.blackboy.controller.util.Result;
import com.blackboy.domain.UserGroup;
import com.blackboy.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    //添加分组信息
    @PostMapping
    public Result addGroup(@RequestBody UserGroup group){
        try {
            return new Result(true,"添加成功",userGroupService.save(group));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    //修改分组信息
    @PutMapping
    public Result updateGroup(@RequestBody UserGroup group){
        try {
            return new Result(true,"修改成功",userGroupService.updateById(group));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    //删除分组信息
    @DeleteMapping("/{groupId}")
    public Result deleteGroup(@PathVariable Integer groupId){
        try {
            //首先移除组内的所有成员
            userGroupService.removeAllUsers(groupId);
            //再删除组本身
            if (userGroupService.delete(groupId)){
                return new Result(true,"删除成功");
            }else{
                return new Result(false,"删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    //启用/禁用分组的周报计划
    @PutMapping("/toggle/{groupId}")
    public Result updateGroupEnabled(@PathVariable Integer groupId){
        try {
            UserGroup group = userGroupService.getById(groupId);
            if (group == null) {
                return new Result(false, "分组不存在");
            }

            // 切换分组的启用状态
            boolean newStatus = !group.isActive();
            group.setActive(newStatus);

            // 更新分组信息到数据库
            if (userGroupService.updateById(group)){
                String message = newStatus ? "启用成功" : "禁用成功";
                return new Result(true, message);
            } else {
                String message = newStatus ? "启用失败" : "禁用失败";
                return new Result(false, message);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"操作失败：" + e.getMessage());
        }
    }

    //获取全部分组信息
    @GetMapping()
    public Result getAllGroup(){
        List<UserGroup> userGroups = userGroupService.list();
        if (userGroups != null){
            return new Result(true,"获取成功",userGroups);
        }else
            return new Result(false,"获取失败");
    }

    //获取分组成员信息
    @GetMapping("/users/{groupId}")
    public Result getGroupUsers(@PathVariable Integer groupId){
        if(userGroupService.getAllUsers(groupId) != null){
            return new Result(true,"获取成功",userGroupService.getAllUsers(groupId));
        }else{
            return new Result(false,"获取失败");
        }
    }

    //将某个用户从分组成员中删除
    @DeleteMapping("/deleteUser/{groupId}/{userId}")
    public Result removeUser(@PathVariable Integer groupId,@PathVariable String userId){
        if (userGroupService.removeUser(groupId,userId)){
            return new Result(true,"删除成功");
        }else{
            return new Result(false,"删除失败");
        }
    }

    //获取不在当前用户组中的用户列表信息
    @GetMapping("/getNotInGroupUsers/{groupId}")
    public Result getNotInGroupUsers(@PathVariable Integer groupId){
        if (userGroupService.getNotInGroupUsers(groupId) != null){
            return new Result(true,"获取成功",userGroupService.getNotInGroupUsers(groupId));
        }else{
            return new Result(false,"获取失败");
        }
    }

    //将选择的多个用户加入到当前分组中
    @PostMapping("/addUsers/{groupId}")
    public Result addUsers(@PathVariable Integer groupId, @RequestBody AddUsersRequest request){
        try {
            System.out.println("user is :" + request.toString());
            String[] users = request.getUserIds();
            for (String userId : users) {
                boolean b = userGroupService.saveUserToGroup(userId, groupId);
            }
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }


}
