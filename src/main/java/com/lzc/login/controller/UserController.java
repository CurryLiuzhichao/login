package com.lzc.login.controller;


import com.lzc.login.base.ResultInfo;
import com.lzc.login.service.FaceLoginService;
import com.lzc.login.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.util.StringUtils.hasLength;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lzc
 * @since 2022-05-17 09:22:39
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FaceLoginService faceLoginService;


    @ApiOperation(value = "测试接口")
    @ResponseBody
    @PostMapping("test")
    public String test() {
        return "ok";
    }

    @ApiOperation(value = "注册接口")
    @ResponseBody
    @PostMapping("register")
    public ResultInfo register(@RequestBody Map<String, String> map) {
        /**
         * 1、检验用户名是否重复
         * 2、两次密码是否一致
         * 3、
         */
        ResultInfo resultInfo = new ResultInfo();
        if (!(hasLength(map.get("username")) || hasLength(map.get("password")) || hasLength(map.get("twopassword")))) {
            resultInfo.setMsg("必填项为空，请重试！");
            return resultInfo;
        }
        //验证用户名是否存在
        if (!userService.checkRepeat(map.get("username"))) {
            resultInfo.setMsg("用户名已存在请重新输入");
            return resultInfo;
        }
        //验证密码是否一致
        if (!map.get("password").equals(map.get("twopassword"))){
            resultInfo.setMsg("两次密码不一致请重新输入");
            return resultInfo;
        }
        //添加数据
        int updates = userService.register(map.get("username"), map.get("password"));
        if (updates == 1) {
            resultInfo.setMsg("注册成功，请返回登陆！");
            return resultInfo;
        }
        resultInfo.setMsg("注册失败请重试");
        return resultInfo;

    }

    @ApiOperation(value = "登录接口")
    @ResponseBody
    @PostMapping("login")
    public ResultInfo Login(@RequestBody Map<String, String> map) {
        ResultInfo resultInfo = new ResultInfo();
        if (!(hasLength(map.get("username")) || hasLength(map.get("password")))) {
            resultInfo.setMsg("必填项为空，请重试！");
            return resultInfo;
        }
        /**
         * 验证信息进行登录 TODO 加入验证码功能
         */
        resultInfo.setMsg(userService.login(map.get("username"),map.get("password")));
        return resultInfo;
    }

    @ApiOperation(value = "人脸注册-账号注册后进行人脸注册")
    @ResponseBody
    @PostMapping("face-register")
    public ResultInfo faceRegister(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, HttpServletRequest request ,@RequestParam(name = "userid") String userid){
        ResultInfo resultInfo = new ResultInfo();
        String str = faceLoginService.registerFace(userid, imagebast64);
        resultInfo.setMsg(userid+"用户"+str);
        return resultInfo;
    }

}
