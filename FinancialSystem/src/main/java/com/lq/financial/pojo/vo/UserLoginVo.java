package com.lq.financial.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  17:29
 * @Description: TODO
 * @Version: 1.0
 */
@ApiModel(value="UserLoginVo",description="用户对象UserLoginVo")
@Data
public class UserLoginVo {
    @ApiModelProperty(value="邮箱",name="email",example="email")
    private String email;

    @ApiModelProperty(value="密码",name="password",example="password")
    private String password;

    @ApiModelProperty(value = "验证码",name = "code",example = "code")
    private String code;

    @ApiModelProperty(value="验证码标识",name="codeflag",example="codeflag")
    private String codeflag;
}
