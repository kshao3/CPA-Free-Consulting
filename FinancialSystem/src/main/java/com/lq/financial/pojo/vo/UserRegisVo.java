package com.lq.financial.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  14:16
 * @Description: 注册实体类
 * @Version: 1.0
 */
@Data
public class UserRegisVo {
    @ApiModelProperty(value="邮箱",name="email",example="email")
    private String email;

    @ApiModelProperty(value="密码",name="password",example="password")
    private String password;

    @ApiModelProperty(value="名称",name="name",example="name")
    private String name;

    @ApiModelProperty(value="验证码",name="code",example="code")
    private String code;
}
