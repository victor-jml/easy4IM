package pers.enoch.im.common.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author yang.zhao
 * @Date 2020/12/18 09:59
 * @Version 1.0
 * @Description
 **/
@Component
public class RedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 维护一个本类的静态变量
     */
    private static RedisService redisService;

    @PostConstruct
    public void init() {
        redisService = this;
        redisService.stringRedisTemplate = this.stringRedisTemplate;
        redisService.redisTemplate = this.redisTemplate;
    }

    /**
     * 将参数中的字符串值设置为键的值，不设置过期时间
     * @param key
     * @param value 必须要实现 Serializable 接口
     */
    public static void set(String prefix ,String key, String value) {
        redisService.stringRedisTemplate.opsForValue().set(prefix + key, value);
    }

    /**
     * 将参数中的字符串值设置为键的值，设置过期时间
     * @param key
     * @param value 必须要实现 Serializable 接口
     * @param timeout
     */
    public static void set(String prefix , String key, String value, Long timeout) {
        redisService.stringRedisTemplate.opsForValue().set(prefix + key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取与指定键相关的值
     * @param key
     * @return
     */
    public static Object get(String prefix,String key) {
        return redisService.stringRedisTemplate.opsForValue().get(prefix + key);
    }

    /**
     * 设置某个键的过期时间
     * @param key 键值
     * @param ttl 过期秒数
     */
    public static boolean expire(String prefix , String key, Long ttl) {
        Boolean expire = redisService.stringRedisTemplate.expire(prefix + key, ttl, TimeUnit.MINUTES);
        return expire != null;
    }

    public static Long getExpire(String prefix ,String key){
        return redisService.stringRedisTemplate.getExpire(prefix + key);
    }

    /**
     * 判断某个键是否存在
     * @param key 键值
     */
    public static boolean hasKey(String prefix ,String key) {
        Boolean aBoolean = redisService.redisTemplate.hasKey(prefix + key);
        return aBoolean != null;
    }

    /**
     * 向集合添加元素
     * @param key
     * @param value
     * @return 返回值为设置成功的value数
     */
    public static Long sAdd(String prefix,String key, String... value) {
        return redisService.redisTemplate.opsForSet().add(prefix + key, value);
    }

    /**
     * 获取集合中的某个元素
     * @param key
     * @return 返回值为redis中键值为key的value的Set集合
     */
    public static Set<String> sGetMembers(String prefix, String key) {
        return redisService.redisTemplate.opsForSet().members(prefix + key);
    }

    /**
     * 将给定分数的指定成员添加到键中存储的排序集合中
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static Boolean zAdd(String key, String value, double score) {
        return redisService.redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 返回指定排序集中给定成员的分数
     * @param key
     * @param value
     * @return
     */
    public static Double zScore(String prefix,String key, String value) {
        return redisService.redisTemplate.opsForZSet().score(prefix + key, value);
    }

    /**
     * 删除指定的键
     * @param key
     * @return
     */
    public static Boolean delete(String prefix ,String key) {
        return redisService.redisTemplate.delete(prefix + key);
    }

    /**
     * 删除多个键
     * @param keys
     * @return
     */
    public static Long delete(Collection<String> keys) {
        return redisService.redisTemplate.delete(keys);
    }

}
