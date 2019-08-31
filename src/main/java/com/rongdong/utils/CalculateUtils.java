package com.rongdong.utils;

import java.math.BigDecimal;

/**
 * 计算工具类
 *
 * @author hsh
 * @create 2018-04-01 22:53
 **/
public class CalculateUtils {
    public static double add(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

// 进行减法运算

    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

// 进行加法运算

    public static double mul(double d1, double d2) {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

// 进行除法运算

    public static double div(double d1, double d2, int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

// 进行四舍五入操作

    public static double round(double d, int len) {     // 进行四舍五入操作
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        String two="1000";
        String one="8";
        double subtract = subtract(Double.parseDouble(one), Double.parseDouble(two));
        System.out.println(subtract);
    }
}
