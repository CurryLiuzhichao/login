package com.lzc.login.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.util.StringUtils.hasLength;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzc
 * @since 2022-05-17 09:22:39
 */
@RestController
@RequestMapping("/user")
public class UserController {



    @ApiOperation(value = "测试接口")
    @ResponseBody
    @PostMapping("test")
    public String test(){
        return "ok";
    }

    @ApiOperation(value = "注册接口")
    @ResponseBody
    @PostMapping("register")
    public String register(@RequestBody Map<String,String> map){
        /**
         * 1、检验用户名是否重复
         * 2、两次密码是否一致
         * 3、
         */
        return "";

    }

    @ApiOperation(value = "登录接口")
    @ResponseBody
    @PostMapping("login")
    public String Login(@RequestBody Map<String,String> map){
        if (!(hasLength(map.get("username"))||hasLength(map.get("password")))){

            return "用户名密码不能为空！";
        }

        return "";
    }

}
