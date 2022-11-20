package com.lq.financial.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-17  16:26
 * @Description: TODO
 * @Version: 1.0
 */
@ApiModel(value="user对象",description="用户对象user")
@Data
public class UserVo {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(value="邮箱",name="email",example="email")
    private String email;

    @ApiModelProperty(value="密码",name="password",example="password")
    private String password;

    @ApiModelProperty(value="验证码标识",name="codeflag",example="codeflag")
    private String codeflag;
}
