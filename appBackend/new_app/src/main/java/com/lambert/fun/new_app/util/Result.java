package com.lambert.fun.new_app.util;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 3165630363040178390L;
    private byte status; // 状态码
    private String msg; // 返回的消息
    private Object data; // 数据
    static Map<String, Object> map; // 容器

    public Result() {

    }

    // 请求成功，自定义返回数据
    public static Map ok(Object data) {
        map = new HashMap<>(ResultCode.OK.getMap());
        if (data != null)
            map.put("data", data);
        return map;
    }

    // 请求成功，自定义返回信息 message
    public static Map ok(String msg) {
        map = new HashMap<>(ResultCode.OK.getMap());
        if (msg != "")
            map.put("message", msg);
        return map;
    }

    // 请求成功，自定义成功类型以及返回的数据
    public static Map ok(ResultCode resultCode, Object data) {
        map = new HashMap<>(resultCode.getMap());
        if (data != null)
            map.put("data", data);
        return map;
    }

    // 请求错误，自定义错误类型
    public static Map no(ResultCode resultCode) {
        map = new HashMap<>(resultCode.getMap());
        return map;
    }

    // 请求错误，自定义错误类型以及消息
    public static Map no(ResultCode resultCode, String msg) {
        map = new HashMap<>(resultCode.getMap());
        map.put("message", msg);
        return map;
    }
}
