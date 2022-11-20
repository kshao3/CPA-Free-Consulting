package com.lq.financial.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  16:17
 * @Description: 文件
 * @Version: 1.0
 */
@TableName(value = "accelist")
@ApiModel(value="AcceList",description="文件对象AcceList")
@Data
public class AcceList {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(value="文件标题",name="title",example="title")
    private String title;

    @ApiModelProperty(value="文件详细描述",name="detailsdescript",example="detailsdescript")
    private String detailsdescript;

    @ApiModelProperty(value="文件路径",name="filepath",example="filepath")
    private String filepath;

    @ApiModelProperty(value="文件大小",name="filesize",example="filesize")
    private String filesize;

    @ApiModelProperty(value="语言类型",name="languages",example="languages")
    private String languages;

    @ApiModelProperty(value="书籍作者",name="author",example="author")
    private String author;

    @TableField(insertStrategy = FieldStrategy.DEFAULT)
    @ApiModelProperty(hidden = true)
    private String createtime;

    @ApiModelProperty(hidden = true)
    private int uid;
}