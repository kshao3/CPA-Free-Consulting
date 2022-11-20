package com.lq.financial.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 心之所向
 * @Date2022/5/16 10:19
 * @Version V1.0
 * 描述这个类的功能
 **/
@ApiModel(value="QueryInfo",description="文件对象QueryInfo")
@Data
public class QueryInfo {
    @ApiModelProperty(value="查询信息",name="query",example="query")
    private String query;     //查询信息 username

    @ApiModelProperty(value="当前页",name="pageNum",example="pageNum")
    private int pageNum = 1;  //当前页

    @ApiModelProperty(value="每页最大数",name="pageSize",example="pageSize")
    private int pageSize = 1; //每页最大数

    @ApiModelProperty(value="查询类型",name="selecttype",example="selecttype")
    private String selecttype;  //查询类型


    public static void main(String[] args) {
        int []array = new int[50];
        List<Integer> total = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(1); list.add(1); list.add(6);list.add(6);list.add(6);list.add(6);list.add(3); list.add(1);list.add(2);list.add(1);list.add(5);
        int count = 1,m=0;
        System.out.println(list);

        Integer result = 0;
        for (int i = 1; i < list.size(); i++) {
             if (list.get(i) == list.get(i-1)){
                 count++;
             }else {
                 count = 1;
             }
             if (m < count){  m = count;  result = list.get(i); }
        }
        System.out.println("最大字串为：" + result  + "---"  + "数量为:" + m);
    }



}
