package com.blackboy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackboy.domain.User;

public interface UserService extends IService<User> {
    IPage<User> getPage(int currentPage, int pageSize);

    IPage<User> getPage(Integer currentPage, Integer pageSize, User user);

    boolean userIsExist(String userId, String password);

    User userLogin(String userId, String EncryptPassword);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteByUserId(String userId);

    boolean resetUserPassword(String userId, String oldPassword,String newPassword);

    User getUserByUserId(String userId);

    boolean setUserAvatar(String userId, String avatarName);
}
