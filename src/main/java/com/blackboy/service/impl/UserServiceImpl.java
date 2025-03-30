package com.blackboy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackboy.mapper.UserGroupMappingMapper;
import com.blackboy.mapper.UserMapper;
import com.blackboy.domain.User;
import com.blackboy.service.UserService;
import com.blackboy.util.PasswordEncryptor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    private final UserGroupMappingMapper userGroupMappingMapper;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    public UserServiceImpl(UserMapper userMapper, UserGroupMappingMapper userGroupMappingMapper) {
        this.userMapper = userMapper;
        this.userGroupMappingMapper = userGroupMappingMapper;
    }

    @Override
    public boolean userIsExist(String userId, String password) {
        LambdaQueryWrapper<User> lq = new LambdaQueryWrapper<User>();
        lq.eq(User::getUserId, userId);
        lq.eq(User::getPassword, password);
        return userMapper.exists(lq);
    }

    @Override
    public User userLogin(String userId, String EncryptPassword) {
        //获取用户的密码进行验证
        LambdaQueryWrapper<User> lq = new LambdaQueryWrapper<User>();
        lq.eq(User::getUserId, userId);

        User user = userMapper.selectOne(lq);
        //比较用户的密码
        if (user != null) {
            //比较用户输入的密码和数据库中的密码是否一致
            if (passwordEncryptor.checkPassword(EncryptPassword, user.getPassword())) {
                System.out.println("比较结果为真。");
                return user;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        //对用户密码进行二次加密
        user.setPassword(passwordEncryptor.encryptPasswordForBCrypt(user.getPassword()));
        if (userMapper.insert(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        //对用户密码进行二次加密
        user.setPassword(passwordEncryptor.encryptPasswordForBCrypt(user.getPassword()));
        if (userMapper.updateByUserId(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByUserId(String userId) {
        // 先删除 user_group_mapping 表中关联的记录
        userGroupMappingMapper.deleteByUserId(userId);
        // 再删除 user 表中的记录
        if (userMapper.deleteByUserId(userId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean resetUserPassword(String userId, String oldPassword, String newPassword) {
        //通过用户名和老密码，确认该用户是否存在，如果存在，才执行重置密码操作
        LambdaQueryWrapper<User> lq = new LambdaQueryWrapper();
        lq.eq(Strings.isNotEmpty(userId), User::getUserId, userId);
        User user = userMapper.selectOne(lq);
        if (user != null) {
            //比较用户输入的密码和数据库中的密码是否一致
            if (passwordEncryptor.checkPassword(oldPassword, user.getPassword())) {
                System.out.println("比较结果为真。");
                //执行重置密码操作
                if (userMapper.resetPassword(userId, passwordEncryptor.encryptPasswordForBCrypt(newPassword)) > 0) {
                    return true;
                }else {
                    return false;
                }
            }
            else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public User getUserByUserId(String userId) {
        if (userMapper.selectByUserId(userId) != null) {
            return userMapper.selectByUserId(userId);
        }else{
            return null;
        }
    }

    @Override
    public IPage<User> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        return this.page(page);
    }

    @Override
    public IPage<User> getPage(Integer currentPage, Integer pageSize, User user) {
        LambdaQueryWrapper<User> lq = new LambdaQueryWrapper<User>();
        lq.like(Strings.isNotEmpty(user.getUserId()), User::getUserId, user.getUserId());
        IPage page = new Page(currentPage, pageSize);
        return this.page(page,lq);
    }


}
