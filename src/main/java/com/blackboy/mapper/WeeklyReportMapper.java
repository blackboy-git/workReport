package com.blackboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackboy.domain.WeeklyReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface WeeklyReportMapper extends BaseMapper<WeeklyReport> {

    // 根据用户ID查询用户所在组的周报
    @Select("SELECT wr.* " +
            "FROM weekly_report wr " +
            "JOIN user u ON wr.user_id = u.user_id " +
            "JOIN user_group_mapping ugm ON u.user_id = ugm.user_id " +
            "JOIN user_group ug ON ugm.group_id = ug.id " +
            "WHERE ugm.group_id = (SELECT group_id FROM user_group_mapping WHERE user_id = #{userId}) " +
            "ORDER BY wr.create_time DESC, " +
            "ugm.member_order")
    List<WeeklyReport> selectGroupWeeklyReports(@Param("userId") String userId);

    // 根据用户 ID 查找该用户所在组的全部成员名单
    @Select("SELECT u.user_id AS userId, u.user_name AS userName " +
            "FROM user u " +
            "JOIN user_group_mapping ugm ON u.user_id = ugm.user_id " +
            "JOIN user_group ug ON ugm.group_id = ug.id " +
            "WHERE ugm.group_id = (SELECT group_id FROM user_group_mapping WHERE user_id = #{userId})")
    List<Map<String, Object>> selectGroupMemberByUserId(@Param("userId") String userId);

}
