package pers.enoch.im.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.redis.RedisService;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 14:22
 * @Version 1.0
 * @Description UserInfo Redis Cache
 **/
@Slf4j
public class UserInfoCache {
    /**
     * 默认过期时间为40分钟
     */
    private static final Long DEFAULT_TTL = 40000L;

    /**
     * 默认5分钟自动刷新token过期时间
     */
    private static final Long MIN_TTL = 5000L;

    /**
     * check is login by uid
     * @param uid
     * @return boolean
     */
    public static boolean checkLoginById(String uid){
        String value = (String) RedisService.get(Constant.REDIS_USER_PREFIX,uid);
        if(Strings.isBlank(value) || "0".equals(value)){
            return false;
        }
        flushTtl(uid);
        return true;
    }

    /**
     * check is login by token
     * @param token
     * @return boolean
     */
    public static boolean checkLoginByToken(String token){
        String  value = (String) RedisService.get(Constant.REDIS_USER_PREFIX,token);
        if(Strings.isEmpty(value) || "0".equals(value) ){
            return false;
        }
        flushTtl(token);
        return true;
    }

    /**
     * 用户上线
     * @param uid
     * @param token
     */
    public static void online(String uid,String token){
        log.info("用户 {} 上线",uid);
        // userId - token  not set redis expire
        RedisService.set(Constant.REDIS_USER_PREFIX,uid,token);
        // token - userId set redis expire
        online(token,uid,DEFAULT_TTL);
    }

    /**
     * 用户上线(设置ttl过期时间)
     * @param uid user Account
     * @param token user auth proof
     * @param ttl
     */
    public static void online(String uid,String token,Long ttl){
        RedisService.set(Constant.REDIS_USER_PREFIX ,uid,token,ttl);
    }

    /**
     * 用户下线
     * @param uid
     */
    public static void offline(String uid){
        String token = (String) RedisService.get(Constant.REDIS_USER_PREFIX, uid);
        RedisService.delete(Constant.REDIS_USER_PREFIX,uid);
        RedisService.delete(Constant.REDIS_USER_PREFIX,token);
        log.info("用户 {} 下线",uid);
    }

    /**
     * check token is valid
     * @param validToken user login return auth proof
     * @return Boolean
     */
    public static Boolean checkToken(String uid, String validToken) {
        Object oldToken = RedisService.get(Constant.REDIS_USER_PREFIX,uid);
        if(oldToken == null){
            return false;
        }
        String old = (String)oldToken;
        if(!validToken.equals(old)){
            return false;
        }
        flushTtl(validToken);
        return true;
    }

    /**
     * flush token - userId TTL
     * @param key redis key
     */
    private static void flushTtl(String key){
        RedisService.expire(Constant.REDIS_USER_PREFIX,key,DEFAULT_TTL);
    }

}
