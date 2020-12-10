package com.hjz.utils;

public final class StringUtils {

    /**
     * 判断字符串是否为空
     * @param s
     * @return
     */
    public static boolean isBankQuotes(String s){
        return s == null || s.length() == 0 || "".equals(s.trim());
    }
}
