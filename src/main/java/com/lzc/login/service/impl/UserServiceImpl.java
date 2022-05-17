package com.lzc.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzc.login.entity.User;
import com.lzc.login.mapper.UserMapper;
import com.lzc.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzc.login.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzc
 * @since 2022-05-17 09:22:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(String username, String password) {
        //对密码进行MD5加密
        String md5psd = Md5Utils.code(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5psd);
        return userMapper.insert(user);
    }

    @Override
    public Boolean checkRepeat(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        List<User> users = userMapper.selectList(wrapper);
        if (users.size() != 0) {
            return false;
        }
        return true;
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if (user==null){
            return "用户名不正确重试！";
        }
        if (!user.getPassword().equals(Md5Utils.code(password))){
            return "密码不正确重试！";
        }
        return "登录成功";
    }
}
