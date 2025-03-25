package com.blackboy.controller.util;

import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private Object data;
    private String msg;

    public Result(boolean flag,String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Result(boolean success, Object data) {
        this.flag = success;
        this.data = data;
    }

    public Result(boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean success) {
        this.flag = success;
    }
}