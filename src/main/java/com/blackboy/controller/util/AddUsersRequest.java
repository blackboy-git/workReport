package com.blackboy.controller.util;

import lombok.Data;

//专门用于接收添加组成员请求的类
@Data
public class AddUsersRequest {
    private String[] userIds;

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }
}
