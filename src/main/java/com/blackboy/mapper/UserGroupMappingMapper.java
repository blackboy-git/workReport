package com.blackboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackboy.domain.UserGroupMapping;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGroupMappingMapper extends BaseMapper<UserGroupMapping> {

    //删除数据通过用户id
    @Delete("delete from user_group_mapping where user_id = #{userId}")
    int deleteByUserId(String userId);
}
