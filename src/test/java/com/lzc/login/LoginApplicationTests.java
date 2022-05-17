package com.lzc.login;

import com.lzc.login.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        int lzc = userService.register("lzc", "123");
        System.out.println(lzc);
    }

}
