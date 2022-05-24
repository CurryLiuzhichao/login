package com.lzc.login.controller;

import com.lzc.login.entity.User;
import com.lzc.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @Transactional
    public void insertTest() {
        User user = new User();
        user.setId(999L);
        user.setUsername("lzc");
        try {
            userService.save(user);
            userService.save(user);
        }catch (Exception e){
            System.out.println("发生错误开始回滚！");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            e.printStackTrace();
        }
    }
}
