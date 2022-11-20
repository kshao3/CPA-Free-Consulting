package com.lq.financial.bean;

/**
 * @Author
 * @Date2021/7/25 20:49
 * @Version V1.0
 *   自定义常用数据
 *  使用抽象类去管理一些常量值
 **/
public abstract class ConstantData {

    public static final String REDIS_CACHE = "user:code:";  // 防爬验证码

    public static final String REGIS_CODE_CACHE = "regis:code:"; //注册验证码
}
