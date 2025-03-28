package com.blackboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackboy.domain.GroupWeeklyReport;
import com.blackboy.domain.GroupWeeklyReportDetail;
import com.blackboy.domain.WeeklyReport;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GroupWeeklyReportMapper extends BaseMapper<GroupWeeklyReport> {

    //查询某期周报中的成员周报
    @Select("SELECT wr.* " +
            "FROM weekly_report wr " +
            "JOIN group_weekly_report gwr ON wr.group_id = gwr.group_id " +
            "WHERE gwr.id = #{groupWeeklyReportId} AND CAST(wr.create_time AS DATETIME) = CAST(gwr.create_time AS DATETIME)")
    public List<WeeklyReport> selectMemberWeeklyReportsByGroupReportId(Integer groupWeeklyReportId);

    //更新周报的浏览次数
    @Update("UPDATE group_weekly_report SET view_count = view_count + 1 WHERE id = #{reportId}")
    public boolean updateCountById(Integer reportId);

    // 删除某期周报中的成员周报
    @Delete("DELETE wr " +
            "FROM weekly_report wr " +
            "JOIN group_weekly_report gwr ON wr.group_id = gwr.group_id " +
            "WHERE gwr.id = #{groupWeeklyReportId} AND CAST(wr.create_time AS DATETIME) = CAST(gwr.create_time AS DATETIME)")
    public int deleteMemberWeeklyReportsByGroupReportId(Integer groupWeeklyReportId);
}