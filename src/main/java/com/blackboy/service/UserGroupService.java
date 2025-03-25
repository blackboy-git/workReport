package com.blackboy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackboy.domain.User;
import com.blackboy.domain.UserGroup;

import java.util.List;

public interface UserGroupService extends IService<UserGroup> {
    IPage<UserGroup> getPage(int currentPage, int pageSize);

    IPage<UserGroup> getPage(Integer currentPage, Integer pageSize, UserGroup userGroup);

    boolean delete(Integer id);

    List<User> getAllUsers(Integer groupId);

    // 删除组中的某个用户
    boolean removeUser(Integer groupId, String userId);

    //移除组中的所有用户
    boolean removeAllUsers(Integer groupId);

    //获取不在该用户组中的用户列表信息
    List<User> getNotInGroupUsers(Integer groupId);

    //将用户添加到组中
    boolean saveUserToGroup(String userId,Integer groupId);

    //获取所有启用的用户组
    List<UserGroup> getActiveGroups();

}
