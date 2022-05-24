package com.lzc.login;

import com.lzc.login.entity.User;
import com.lzc.login.service.RedisService;
import com.lzc.login.service.UserService;
import com.lzc.login.util.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

@SpringBootTest
class LoginApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @Test
    void contextLoads() {
        int lzc = userService.register("lzc", "123");
        System.out.println(lzc);
    }

    @Test
    void selecrRedis() {
        String token = "lzc" + "_" + UUID.randomUUID().toString();
        String strMd5 = Md5Utils.code(token);
        Object o = redisService.get("lzc");
        System.out.println(o);
    }

    @Test
    @Transactional
    public void insertTest() {
        User user = new User();
        user.setId(999L);
        user.setUsername("lzc");
        try {
            userService.save(user);
//            userService.save(user);
        }catch (Exception e){
            System.out.println("发生错误开始回滚！");
//            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            e.printStackTrace();
        }
    }

}
