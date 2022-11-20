package com.lq.financial.bean;

/**
 *  枚举状态类
 */
public enum  CodeEnumEntity{

    //自定义的状态码
    DATABASE_EXCEPTION(2000,"数据库连接异常"),
    MONITOR_EXCEPTION(2001,"文件监控异常"),
    OK(200,"成功"),
    SYSTEM_ERROR(10,"系统错误"),
    ERROR(100,"失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    //错误码
    public Integer code;
    //提示信息
    public String message;

    //构造函数
    CodeEnumEntity(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    //获取状态码
    public Integer getCode(){
        return code;
    }
    //获取提示信息
    public String getMessage(){
        return message;
    }
}
