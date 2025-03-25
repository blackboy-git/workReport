package com.blackboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackboy.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where user_id = #{userId}")
    User selectByUserId(String userId);

    @Update("UPDATE `user` " +
            "SET user_name = CASE WHEN #{userName} IS NOT NULL AND #{userName} != '' THEN #{userName} ELSE user_name END, " +
            "password = CASE WHEN #{password} IS NOT NULL AND #{password} != '' THEN #{password} ELSE password END, " +
            "role = CASE WHEN #{role} IS NOT NULL AND #{role} != '' THEN #{role} ELSE role END, " +
            "enabled = CASE WHEN #{enabled} IS NOT NULL THEN #{enabled} ELSE enabled END, " +
            "avatar = CASE WHEN #{avatar} IS NOT NULL AND #{avatar} != '' THEN #{avatar} ELSE avatar END " +
            "WHERE user_id = #{userId}")
    int updateByUserId(User user);

    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    int deleteByUserId(String userId);

    @Update("UPDATE user SET password = #{password} WHERE user_id = #{userId}")
    int resetPassword(String userId, String password);
}
