package com.rongdong.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据转换
 * @author hsh
 * @create 2018-03-31 14:49
 */
public class ResultUtils {

    public static Object parseResult(Object object, Object defaultVal) {
        return object == null ? defaultVal : object;
    }

    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
    }};
        return jsonObject.toString();
    }

    public static String fillResultString(Integer status,  JSONObject result){
        result.put("status", status);
        result.put("result", result);
        return result.toString();
    }


}
