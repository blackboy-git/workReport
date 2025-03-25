package com.blackboy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackboy.domain.GroupWeeklyReport;
import com.blackboy.domain.User;
import com.blackboy.domain.UserGroupMapping;
import com.blackboy.mapper.GroupWeeklyReportMapper;
import com.blackboy.mapper.UserGroupMapper;
import com.blackboy.domain.UserGroup;
import com.blackboy.mapper.UserGroupMappingMapper;
import com.blackboy.mapper.UserMapper;
import com.blackboy.service.UserGroupService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {

    private final UserGroupMapper userGroupMapper;
    private final UserMapper userMapper;

    private  GroupWeeklyReportMapper groupWeeklyReportMapper;

    private UserGroupMappingMapper userGroupMappingMapper;

    public UserGroupServiceImpl(UserGroupMapper userGroupMapper, UserMapper userMapper,
                                GroupWeeklyReportMapper groupWeeklyReportMapper,
                                UserGroupMappingMapper userGroupMappingMapper) {
        this.userGroupMapper = userGroupMapper;
        this.userMapper = userMapper;
        this.groupWeeklyReportMapper = groupWeeklyReportMapper;
        this.userGroupMappingMapper = userGroupMappingMapper;
    }

    @Override
    public IPage<UserGroup> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        return this.page(page);
    }

    @Override
    public IPage<UserGroup> getPage(Integer currentPage, Integer pageSize, UserGroup userGroup) {
        LambdaQueryWrapper<UserGroup> lq = new LambdaQueryWrapper<UserGroup>();
        lq.like(Strings.isNotEmpty(userGroup.getGroupName()), UserGroup::getGroupName, userGroup.getGroupName());
        IPage page = new Page(currentPage, pageSize);
        return this.page(page,lq);
    }

    @Override
    public boolean delete(Integer id) {
        try{
            //        return userGroupMapper.deleteById(id) > 0;
            // 先删除 group_weekly_report 表中与该分组相关的记录
            QueryWrapper<GroupWeeklyReport> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_id", id);
            groupWeeklyReportMapper.delete(queryWrapper);

            // 再删除 user_group_mapping 表中与该分组相关的记录
            QueryWrapper<UserGroupMapping> mappingQueryWrapper = new QueryWrapper<>();
            mappingQueryWrapper.eq("group_id", id);
            userGroupMappingMapper.delete(mappingQueryWrapper);

            // 最后删除 user_group 表中的记录
            userGroupMapper.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    //获取用户所在组的全部成员
    @Override
    public List<User> getAllUsers(Integer groupId) {
        return userGroupMapper.getAllUsers(groupId);
    }

    @Override
    public boolean removeUser(Integer groupId, String userId) {
        if (userGroupMapper.deleteUser(groupId, userId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAllUsers(Integer groupId) {
        if (userGroupMapper.deleteAllUsers(groupId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getNotInGroupUsers(Integer groupId) {
        if (userGroupMapper.getNotInGroupUsers(groupId) != null){
            return userGroupMapper.getNotInGroupUsers(groupId);
        }
        return List.of();
    }

    @Override
    public boolean saveUserToGroup(String userId, Integer groupId) {
        // 获取当前组中最大的 member_order 值
        Integer maxMemberOrder = userGroupMapper.getMaxMemberOrder(groupId);
        // 新用户的 member_order 值为最大 member_order 值加 1
        int memberOrder = maxMemberOrder + 1;
        if(userGroupMapper.saveUserToGroup(userId, groupId, memberOrder) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<UserGroup> getActiveGroups() {
        QueryWrapper<UserGroup> qr = new QueryWrapper<UserGroup>();
        qr.eq("is_active", true);
        List<UserGroup> activeGroups = userGroupMapper.selectList(qr);
        return activeGroups != null ? activeGroups : List.of();
    }
}
