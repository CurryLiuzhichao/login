package com.lzc.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzc.login.base.ResultInfo;
import com.lzc.login.entity.User;

import com.lzc.login.mapper.UserMapper;
import com.lzc.login.service.RedisService;
import com.lzc.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzc.login.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private RedisService redisService;

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
        // TODO 验证token是否存在
        String token = username + "_" + UUID.randomUUID().toString();
        String strMd5 = Md5Utils.code(token);
        //TODO key和value顺序问题
        redisService.set(username,strMd5);

        return "登录成功";
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @Override
    public ResultInfo logout(HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        String token = request.getHeader("token");
        Boolean delete = redisService.delete(token);
        if (delete){
            resultInfo.setMsg("退出成功");
        }else {
            resultInfo.setMsg("退出失败");

        }
        return resultInfo;
    }

}
