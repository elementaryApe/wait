package com.rongdong.utils;

import java.io.UnsupportedEncodingException;

/**
 * 编码转换工具类
 *
 * @author hsh
 * @create 2018-04-01 2:16
 **/
public class EncodingUtils {

    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
