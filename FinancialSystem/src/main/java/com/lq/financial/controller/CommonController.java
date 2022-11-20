package com.lq.financial.controller;

import com.lq.financial.bean.CodeEnumEntity;
import com.lq.financial.bean.ResultVo;
import com.lq.financial.service.OssService;
import com.lq.financial.util.FileSizeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 心之所向
 * @Date2022/5/18 15:34
 * @Version V1.0
 *  共同控制器  -  文件上传
 **/
@Api(value = "财务咨询网站-文件上传管理",tags = "财务咨询网站-文件上传管理")
@Controller
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    private OssService ossService;

    //单个文件上传
    @ApiOperation(value = "单个文件上传",notes = "系统-单个文件上传")
    @RequestMapping(value = "/uploadOssfile",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo uploadOssfile(@RequestParam("file")
                 @ApiParam(name = "file",value = "file", required = true)  MultipartFile file){
        String url = "";
        Map<String,Object> map = new HashMap<>();
        long size = file.getSize();
        double v = FileSizeUtil.FileSize(size, FileSizeUtil.SIZETYPE_KB);
        int i = (int)v;
        try {
              url = ossService.uploadFile(file);
        } catch (Exception e) {
            System.out.println("文件传输发生异常:" + e.getMessage());
        }
        map.put("filesize",i);
        map.put("filepath",url);
        return new ResultVo(true, CodeEnumEntity.OK.code,"成功",map);
    }

    //多个文件上传
    @ApiOperation(value = "多个文件上传",notes = "系统-多个文件上传")
    @RequestMapping(value = "/uploadOssMorefile",method = RequestMethod.POST,consumes = "multipart/*",headers = "content-Type=multipart/form-data")
    @ResponseBody
    public ResultVo uploadOssMorefile(@RequestParam("files")MultipartFile []files){
        ResultVo result = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < files.length ; i++) {
            try {
                String URL = ossService.uploadFile(files[i]);
                stringBuilder.append(URL).append("☆");
            } catch (Exception e) {
                System.out.println("文件传输发生异常:" + e.getMessage());
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("☆"));
        return new ResultVo(true,CodeEnumEntity.OK.code,"成功",stringBuilder);
    }

}
