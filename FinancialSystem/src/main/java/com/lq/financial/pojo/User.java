package com.lq.financial.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-09  14:18
 * @Description: User Model
 * @Version: 1.0
 */
@ApiModel(value="user",description="用户对象user")
@TableName("users")
@Data
public class User {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(value="邮箱",name="email",example="email")
    private String email;

    @ApiModelProperty(value="密码",name="password",example="password")
    private String password;

    @ApiModelProperty(value="名称",name="name",example="name")
    private String name;

    @ApiModelProperty(hidden = true)
    private int integral;
}
