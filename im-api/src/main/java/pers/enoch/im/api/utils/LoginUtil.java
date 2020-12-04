package pers.enoch.im.api.utils;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 14:22
 * @Version 1.0
 * @Description 用户登录工具类
 **/
public class LoginUtil {


    /**
     *  创建 token 默认过期时间为15分钟
     * @param uid
     * @return
     */
    public static String createToken(Long uid) {
        return createToken(uid, 15L);
    }

    /**
     * 创建 token
     * @param uid
     * @return
     */
    public static String createToken(Long uid, Long ttl) {
        String token = TokenUtil.makeToken();
        CacheUtil.set(uid.toString(),token,ttl);
        return token;
    }

    /**
     * 检验token是否有效
     * @param validToken 传入的token
     * @return Boolean
     */
    public static Boolean checkToken(Long uid, String validToken) {
        Object oldToken = CacheUtil.get(uid.toString());
        if(oldToken == null){
            return false;
        }
        // 如果token不为空则返回true
        // todo 更新快过期的token
        updateToken(uid);
        return true;
    }

    /**
     * 更新token
     * @param uid 用户id
     */
    public static void updateToken(Long uid){
        String newToken = createToken(uid);
        CacheUtil.set(uid.toString(),newToken);
    }
}
