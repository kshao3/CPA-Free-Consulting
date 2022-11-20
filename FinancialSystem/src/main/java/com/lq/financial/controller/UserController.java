package com.lq.financial.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.code.kaptcha.Producer;
import com.lq.financial.bean.CodeEnumEntity;
import com.lq.financial.bean.ConstantData;
import com.lq.financial.bean.ResultVo;
import com.lq.financial.mapper.UserMapper;
import com.lq.financial.pojo.User;
import com.lq.financial.pojo.vo.UserForgetVo;
import com.lq.financial.pojo.vo.UserLoginVo;
import com.lq.financial.pojo.vo.UserRegisVo;
import com.lq.financial.pojo.vo.UserVo;
import com.lq.financial.service.IMailService;
import com.lq.financial.util.FormUtils;
import com.lq.financial.util.JwtUtil;
import com.lq.financial.util.RandomUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-09  14:35
 * @Description: TODO
 * @Version: 1.0
 */
@Api(value = "财务咨询网站-用户管理",tags = "财务咨询网站-用户管理")
@RequestMapping("user")
@Slf4j
@Controller
public class UserController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IMailService iMailService;

    /* 1、登录 */
    @ApiOperation(value = "登录",notes = "系统-登录")
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultVo login(@RequestBody @ApiParam(name="用户信息对象",value="传入json格式",required=true)
                                  UserLoginVo userVo){
        ResultVo resultVo = null;
        Map<String,Object> map = new HashMap<>();
        //1、验证码是否正确
        String code = redisTemplate.opsForValue().get(ConstantData.REDIS_CACHE + userVo.getCodeflag());
        if (StringUtils.isEmpty(code)){
            resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"验证码已经过期,重新刷新");
        }else {
            if (code.equals(userVo.getCode())){
                //2.判断账号和密码是否正确
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("email",userVo.getEmail());
                queryWrapper.eq("password",userVo.getPassword());
                User user = userMapper.selectOne(queryWrapper);
                if (user != null){
                    String signToken = JwtUtil.sign(user.getName(), String.valueOf(user.getId()));
                    map.put("signToken",signToken);
                    map.put("name",user.getName());
                    resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"成功",signToken);
                }else {
                    resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"账号或密码错误");
                }
            }else {  //如果验证码不一致
                resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"验证码错误或已经过期");
            }
        }
        return resultVo;
    }

    /* 2、生成验证码 */
    @ApiOperation(value = "验证码",notes = "系统-生成防爬验证码")
    @ResponseBody
    @RequestMapping(value = "/VerifiCode",method = RequestMethod.POST)
    public ResultVo VerifiCode(){
        UUID uuid = UUID.randomUUID();
        String capStr2 = null, code2 = null;
        capStr2 = code2 = captchaProducer.createText();
        //3、获取Base64编码
        BufferedImage bi2 = captchaProducer.createImage(capStr2);
        FastByteArrayOutputStream fbaos2 = new FastByteArrayOutputStream();
        try {
            ImageIO.write(bi2, "png", fbaos2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String kaptchaBase642 = Base64.encodeBase64String(fbaos2.toByteArray());
        //4、存入redis中
        redisTemplate.opsForValue().set(ConstantData.REDIS_CACHE + uuid.toString(),code2,60, TimeUnit.SECONDS);
        //5、返回结果集
        Map<String,String> result = new HashMap<>(2);
        result.put("code", uuid.toString());
        result.put("base64", "data:image/png;base64," + kaptchaBase642);
        return new ResultVo(true,CodeEnumEntity.OK.code,"成功",result);
    }

    /* 3、注册 */
    @ApiOperation(value = "注册",notes = "系统-注册")
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultVo register(@RequestBody
                                 @ApiParam(name="用户信息",value="传入json格式",required=true) UserRegisVo user){
        ResultVo resultVo = null;
        //1.判断该邮箱是否已经存在
        QueryWrapper queryWrapper = new QueryWrapper();
        User usermodel = new User();
        queryWrapper.eq("email",user.getEmail());
        User user1 = userMapper.selectOne(queryWrapper);
        String cacheCode = redisTemplate.opsForValue().get(ConstantData.REGIS_CODE_CACHE + user.getEmail());
        if (user1 != null){
            //1.1 邮箱存在 返回失败
            resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"该邮箱已存在");
        }else {
            //1.2 判断验证码是否正确,邮箱不存在 插入到数据库
            if (StringUtils.isEmpty(cacheCode)){  //验证码不存在
                resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"验证码过期");
            }else {
                if (cacheCode.equals(user.getCode())){
                    BeanUtils.copyProperties(user,usermodel);
                    int insert = userMapper.insert(usermodel);
                    resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"成功");
                }else {
                    resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"验证码错误");
                }
            }
        }
        return resultVo;
    }

    /* 4、发送验证码 */
    @ApiOperation(value = "发送验证码",notes = "系统-注册发送邮箱验证码")
    @ResponseBody
    @RequestMapping(value = "/checkoutSendCode/{email}",method = RequestMethod.POST)
    public ResultVo checkoutSendCode(@ApiParam(name = "email", value = "邮箱", required = true)
                                         @PathVariable String email){
        ResultVo resultVo = null;
        System.out.println("邮箱：" + email);
        boolean email1 = FormUtils.isEmail(email);
        String fourBitRandom = RandomUtils.getFourBitRandom();
        Map<String,Object> map = new HashMap<>();
       // if (email1){  //发送验证码
            map.put("code",fourBitRandom);
            try {
                iMailService.sendOnlyRemind(new String[]{email},"验证码",map,"code.html");
                redisTemplate.opsForValue().set(ConstantData.REGIS_CODE_CACHE + email,fourBitRandom,3, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"成功");
      //  }else {
      //      resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"格式不正确");
      //  }
        return resultVo;
    }

    /* 5、忘记密码 */
    @ApiOperation(value = "忘记密码",notes = "系统-忘记密码")
    @ResponseBody
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    public ResultVo forgetPassword(@ApiParam(name = "用户信息", value = "传入json格式", required = true)
                                       @RequestBody UserForgetVo userForgetVo){
        ResultVo resultVo = null;
        User uservo =  new User();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email",userForgetVo.getEmail());
        queryWrapper.eq("password",userForgetVo.getPassword());
        User user = userMapper.selectOne(queryWrapper);   //判断账号和密码是否正确
        if (user != null){
            BeanUtils.copyProperties(userForgetVo,uservo);
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("email",userForgetVo.getEmail())
                    .set("password", userForgetVo.getNewpassword());
            int update = userMapper.update(null, updateWrapper);
            resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"成功");
        }else {
            resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"账号或者密码错误");
        }
        return resultVo;
    }

    /* 6、退出登录 */
    @ApiOperation(value = "退出登录",notes = "系统-退出登录")
    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResultVo logout(@RequestHeader("define-token") String token){
        //删除Token
        ResultVo resultVo = null;
        boolean verify = JwtUtil.verify(token);
        if (verify){
            resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"成功");
        }else {
            resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"Token错误或者过期");
        }
        return resultVo;
    }


    /* 7、显示个人信息界面 */
    @ApiOperation(value = "显示个人信息",notes = "系统-显示个人信息")
    @ResponseBody
    @RequestMapping(value = "/showInfo",method = RequestMethod.GET)
    public ResultVo showInfo(@RequestHeader("define-token") String token){
        ResultVo resultVo = null;
        boolean verify = JwtUtil.verify(token);
        if (verify){
            String userId = JwtUtil.getUserId(token);
            User user = userMapper.selectById(userId);
            if (user!=null){
                user.setPassword("");
                resultVo = new ResultVo(true,CodeEnumEntity.OK.code,"成功",user);
            }else {
                resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"Token错误");
            }
        }else {
            resultVo = new ResultVo(true,CodeEnumEntity.ERROR.code,"Token错误或者过期");
        }
        return resultVo;
    }


    /* 8、修改个人名称 */
    @ApiOperation(value = "修改个人名称信息",notes = "系统-修改个人名称信息")
    @ResponseBody
    @RequestMapping(value = "/updateUserByName/{name}",method = RequestMethod.GET)
    public ResultVo updateUserByName(@RequestHeader("define-token") String token
            ,@ApiParam(name = "name", value = "用户名", required = true) @PathVariable String name){
        String userId = JwtUtil.getUserId(token);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",Integer.parseInt(userId))
                .set("name", name);
        int update = userMapper.update(null, updateWrapper);
        return new ResultVo(true,CodeEnumEntity.OK.code,"成功");
    }




}
