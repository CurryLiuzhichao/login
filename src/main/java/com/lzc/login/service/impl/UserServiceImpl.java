package com.lzc.login.service.impl;

import com.lzc.login.entity.User;
import com.lzc.login.mapper.UserMapper;
import com.lzc.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
