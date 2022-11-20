package com.lq.financial.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  10:25
 * @Description: TODO
 * @Version: 1.0
 */
@ApiModel(value="UserForgetVo",description="对象UserForgetVo")
@Data
public class UserForgetVo {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(value="邮箱",name="email",example="email")
    private String email;

    @ApiModelProperty(value="旧密码",name="password",example="password")
    private String password;

    @ApiModelProperty(value="新密码",name="newpassword",example="newpassword")
    private String newpassword;
}
