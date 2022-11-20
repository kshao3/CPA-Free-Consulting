package com.lq.financial.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lq.financial.bean.CodeEnumEntity;
import com.lq.financial.bean.ResultVo;
import com.lq.financial.mapper.AcceListMapper;
import com.lq.financial.mapper.HistoricalRecordMapper;
import com.lq.financial.mapper.UserMapper;
import com.lq.financial.pojo.AcceList;
import com.lq.financial.pojo.HistoricalRecord;
import com.lq.financial.pojo.QueryInfo;
import com.lq.financial.pojo.User;
import com.lq.financial.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  15:50
 * @Description: 文件管理
 * @Version: 1.0
 */
@Api(value = "财务咨询网站-文件管理",tags = "财务咨询网站-文件管理")
@Controller
@RequestMapping(value = "file")
public class FileController {

    @Autowired(required = false)
    private AcceListMapper acceListMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private HistoricalRecordMapper historicalRecordMapper;

    /* 1、文件添加 */
    @ApiOperation(value = "文件添加",notes = "系统-文件添加")
    @RequestMapping(value = "/addFile",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo addFile(
            @ApiParam(name = "文件信息", value = "传入json格式", required = true)
            @RequestBody AcceList acceList, @RequestHeader("define-token") String token){
        ResultVo resultVo = null;

        boolean verify = JwtUtil.verify(token);
        if (verify){
            acceList.setFilesize(acceList.getFilesize() + "KB");
            String userId = JwtUtil.getUserId(token);
            acceList.setUid(Integer.parseInt(userId));
            int i = userMapper.updateIntegral(Integer.parseInt(userId)); //修改积分
            int insert = acceListMapper.insert(acceList);
            resultVo = new ResultVo(true, CodeEnumEntity.OK.code,"文件上传成功,积分+1");
        }else {
            resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"Token不正确或过期,请重新登录!");
        }
        return resultVo;
    }

    /* 2、文件列表展示-分页显示 */
    @ApiOperation(value = "文件列表显示",notes = "系统-列表显示")
    @RequestMapping(value = "/listFile",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo listFile(
            @ApiParam(name = "文件分页", value = "传入json格式", required = true)
            @RequestBody QueryInfo queryInfo){
        List<AcceList> acceLists = null;
        PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
        if (queryInfo.getSelecttype().equals("like")){
            acceLists = acceListMapper.selectAllByLike(queryInfo);//模糊查询
        }else {
            acceLists = acceListMapper.selectAllByAll(queryInfo);//精确查询
        }
        PageInfo pageInfo = new PageInfo(acceLists);
        return new ResultVo(true,CodeEnumEntity.OK.code,"成功",pageInfo);
    }

    /* 3、添加文件下载历史 */
    @ApiOperation(value = "添加文件下载历史",notes = "系统-添加文件下载历史")
    @RequestMapping(value = "/showHistorical/{fileid}",method = RequestMethod.GET)
    @ResponseBody
    public ResultVo showHistorical(
            @RequestHeader("define-token") String token,@PathVariable int fileid){
        HistoricalRecord historicalRecord = new HistoricalRecord();
        String userId = JwtUtil.getUserId(token);
        historicalRecord.setAccelistid(fileid);
        historicalRecord.setUid(Integer.parseInt(userId));
        //如果用户下载了文件  则用户积分减1
        userMapper.updateSubtract(Integer.parseInt(userId));
        historicalRecordMapper.insert(historicalRecord);
        return new ResultVo(true,CodeEnumEntity.OK.code,"成功");
    }

    /* 4、展示文件下载历史 */
    @ApiOperation(value = "展示文件下载历史",notes = "系统-展示文件下载历史")
    @RequestMapping(value = "/showHistorical",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo showHistorical(
            @RequestHeader("define-token") String token,
            @ApiParam(name = "文件历史分页", value = "传入json格式", required = true)
            @RequestBody QueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
        String userId = JwtUtil.getUserId(token);
        List<AcceList> acceLists = historicalRecordMapper.showHistoricalAll(Integer.parseInt(userId));
        PageInfo pageInfo = new PageInfo(acceLists);
        return new ResultVo(true,CodeEnumEntity.OK.code,"成功",pageInfo);
    }

}
