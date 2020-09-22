package com.yaorange.utils;

/**
 * @ClassNameStringUtils
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/14 16:41
 * @Version 1.0
 **/
public class StringUtils {
    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        } else if (string.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
