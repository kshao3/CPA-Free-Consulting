package com.lq.financial.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author
 * @Date2021/1/5 20:12
 * @Version V1.0
 *  返回值
 **/
public class ResultVo<T> implements Serializable {
    @ApiModelProperty(value = "是否成功")
    private boolean flag;//是否成功
    @ApiModelProperty(value = "状态码")
    private Integer code;//返回码
    @ApiModelProperty(value = "返回消息")
    private String msg;//返回消息
    @ApiModelProperty(value = "返回数据")
    private T data;//返回数据

    public ResultVo(boolean flag, Integer code, String msg, Object data) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = (T) data;
    }

    public ResultVo(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
    }

    public ResultVo() {
        this.flag = true;
        this.code = CodeEnumEntity.OK.getCode();
        this.msg = "操作成功!";
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
