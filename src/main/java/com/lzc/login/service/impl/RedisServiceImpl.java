package com.lzc.login.service.impl;

import com.lzc.login.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate redisTemplate;

    /*
     * 设置key并且设置有效时间
     * */
    public void set(String key, Object value) {
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.opsForValue().set(key, value, 60 * 60 * 24, TimeUnit.MINUTES);//设置key并且设置有效时间;
    }

    /*
     * 删除key
     * */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /*
     * 更新key
     * */
    public Boolean update(String key) {

        return redisTemplate.expire(key, 60 * 60 * 24, TimeUnit.MINUTES);
    }

    /*
     * 查询key
     * */
    public Object get(String key) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

}
