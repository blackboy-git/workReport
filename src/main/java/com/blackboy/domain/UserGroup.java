package com.blackboy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
//@Accessors(chain = true)
@TableName("user_group")
public class UserGroup {

    @TableId("id")
    private Integer id;

    @TableField("group_name")
    private String groupName;

    @TableField("report_plan_name")
    private String reportPlanName;

    @TableField("start_date")
    private LocalDate startDate;

    @TableField("cycle_days")
    private Integer cycleDays;

    @TableField("allow_days")
    private Integer allowDays;

    @TableField("is_active")
    private boolean isActive = false;

    // 修改 setStartDate 方法，处理日期格式转换
    public void setStartDate(String startDate) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy,MM,d"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate dateTime = LocalDate.parse(startDate, formatter);
                this.startDate = dateTime;
                return;
            } catch (DateTimeParseException e) {
                // 继续尝试下一个格式
            }
        }
        // 如果所有格式都解析失败，设置为 null
        this.startDate = null;
    }

    public String getStartDate() {
        if (startDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return startDate.format(formatter);
        }
        return null;
    }
}