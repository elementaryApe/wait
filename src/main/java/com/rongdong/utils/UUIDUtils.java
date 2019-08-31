package com.rongdong.utils;

import java.util.UUID;

/**
 * UUID
 * @author hsh
 * @create 2018-04-01 0:43
 **/
public class UUIDUtils {

    public UUIDUtils() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
