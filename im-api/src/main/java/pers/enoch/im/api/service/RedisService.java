package pers.enoch.im.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @Author: zy
 * @Date: 2020/12/9 22:55
 * @Description:
 */
public class RedisService {
    @Resource
    StringRedisTemplate stringRedisTemplate;


}
