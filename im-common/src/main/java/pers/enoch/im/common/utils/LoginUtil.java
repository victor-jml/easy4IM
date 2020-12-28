package pers.enoch.im.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import pers.enoch.im.common.constant.Constant;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 14:22
 * @Version 1.0
 * @Description 用户登录工具类
 **/
@Slf4j
public class LoginUtil {
    /**
     * 默认过期时间为40分钟
     */
    private static final Long DEFAULT_TTL = 40000L;

    /**
     * 默认5分钟自动刷新token过期时间
     */
    private static final Long MIN_TTL = 5000L;

    /**
     * 检查是否已经登录
     * @param uid
     * @return boolean
     */
    public static boolean checkLogin(String uid){
        String value = (String)RedisUtil.get(Constant.REDIS_USER_PREFIX,uid);
        if(Strings.isBlank(value)){
            return false;
        }
        return !"0".equals(value);
    }

    /**
     * 用户上线（token过期时间默认40分钟）
     * @param uid
     * @param token
     */
    public static void online(String uid,String token){
        online(uid,token,DEFAULT_TTL);
    }

    /**
     * 用户上线
     * @param uid
     * @param token
     * @param ttl
     */
    public static void online(String uid,String token,Long ttl){
        RedisUtil.set(Constant.REDIS_USER_PREFIX ,uid,token,ttl);
        log.info("用户 {} 上线",uid);
    }

    /**
     * 用户下线
     * @param uid
     */
    public static void offline(String uid){
        RedisUtil.delete(Constant.REDIS_USER_PREFIX,uid);
        log.info("用户 {} 下线",uid);
    }

    /**
     * 检验token是否有效,每一次都会更新过期时间
     * @param validToken 传入的token
     * @return Boolean
     */
    public static Boolean checkToken(String uid, String validToken) {
        Object oldToken = RedisUtil.get(Constant.REDIS_USER_PREFIX,uid);
        if(oldToken == null){
            return false;
        }
        String old = (String)oldToken;
        if(!validToken.equals(old.split(",")[0])){
            return false;
        }
        // 如果token不为空则返回true
        Long ttl = RedisUtil.getExpire(Constant.REDIS_USER_PREFIX,uid);
        RedisUtil.expire(Constant.REDIS_USER_PREFIX,uid,DEFAULT_TTL);
        log.info("用户 {} 更新token过期时间",uid);
        return true;
    }

}
