package com.lq.financial.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 刘庆
 * @Date2021/10/11 14:40
 * @Version V1.0
 *  自定义验证类
 **/
public class FormUtils {

    /**
     * 手机号验证
     */
    public static boolean isMobile(String str) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 邮箱验证
     */
    public static boolean isEmail(String str) {
       // Pattern p = Pattern.compile("^\\w+@[a-z0-9]+\\.[a-z]{2,4}$"); // 验证邮箱
        Pattern p = Pattern.compile("^[a-zA-Z][\\w_]{5,17}@(163|126|qq)\\.com");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) {
        boolean email = isEmail("630566964@qq.com");
        System.out.println(email);
    }

}
