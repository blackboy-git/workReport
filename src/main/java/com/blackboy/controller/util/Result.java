package com.blackboy.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private Object data;
    private String msg;
    private String token;

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

    // 转为json格式
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // 可以根据实际需求添加更详细的异常处理逻辑，这里简单地将异常继续抛出
            throw e;
        }
    }

}