package com.ql.p2p.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wanqiuli
 * @date 2022/7/21 21:46
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    public String getStr(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return (String) value;
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }
}
