package com.rongdong.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串操作工具类
 * Created by hansh on 2017/9/27.
 */
public class StringUtil {

    /**
     * 加密字符串保留指定位数
     * @param source 数据源
     * @param begin  保留前几位开始加密
     * @param end   保留后几位
     */
    public static String encryptString(String source,Integer begin,Integer end){
        return StringUtils.isNotBlank(source) ? source.substring(0, begin)
                + source.substring(begin-1, source.length() - end).replaceAll("([\\w\\W])", "*")
                + source.substring(source.length() - end) : source;
    }

    public static void main(String[] args) {
        System.out.println(encryptString("韩",1,0));
    }
}
