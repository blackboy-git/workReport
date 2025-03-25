package com.blackboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackboy.domain.User;
import com.blackboy.domain.UserGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {

    //查询用户组里面的所有用户
    @Select("SELECT u.user_id,u.user_name " +
            "FROM user u " +
            "JOIN user_group_mapping ugm ON u.user_id = ugm.user_id " +
            "JOIN user_group ug ON ugm.group_id = ug.id " +
            "WHERE ug.id = #{groupId}")
    List<User> getAllUsers(Integer groupId);

    @Delete("DELETE FROM user_group_mapping WHERE group_id = #{groupId} AND user_id = #{userId}")
    Integer deleteUser(Integer groupId, String userId);

    //移除组中的所有用户
    @Delete("DELETE FROM user_group_mapping WHERE group_id = #{groupId}")
    Integer deleteAllUsers(Integer groupId);

    //获取不在该用户组中的用户列表信息
    @Select("SELECT u.user_id, u.user_name " +
            "FROM user u " +
            "LEFT JOIN user_group_mapping ugm ON u.user_id = ugm.user_id AND ugm.group_id = #{groupId} " +
            "WHERE ugm.user_id IS NULL")
    List<User> getNotInGroupUsers(Integer groupId);

    /**
     * 获取指定组中最大的 member_order 值
     */
    @Select("SELECT COALESCE(MAX(member_order), 0) FROM user_group_mapping WHERE group_id = #{groupId}")
    Integer getMaxMemberOrder(Integer groupId);

    /**
     * 将用户添加到指定组中，并设置 member_order
     */
    @Insert("INSERT INTO user_group_mapping (user_id, group_id, member_order) VALUES (#{userId}, #{groupId}, #{memberOrder})")
    int saveUserToGroup(String userId, Integer groupId,Integer memberOrder);
}
