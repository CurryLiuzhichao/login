package com.lzc.login.service;

import com.lzc.login.base.ResultInfo;
import com.lzc.login.dto.LoginDTO;
import com.lzc.login.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzc
 * @since 2022-05-17 09:22:39
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return
     */
    int register(String username, String password);

    /**
     * 检查用户名是否重复
     * @param username
     * @return
     */
    Boolean checkRepeat(String username);

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 退出登录
     * @param request
     * @return
     */
    ResultInfo logout(HttpServletRequest request);
    
}
