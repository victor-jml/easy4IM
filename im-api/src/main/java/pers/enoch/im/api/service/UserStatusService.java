package pers.enoch.im.api.service;


/**
 * @author yang.zhao
 */
public interface UserStatusService {

    /**
     * 用户上线
     * @param uid 用户id
     * @param token 令牌
     */
    void online(String uid, String token);

    /**
     * 用户离线
     * @param uid
     */
    void offline(String uid);

    /**
     * 调用登录API时，检验该用户是否已经登录
     * @param uid
     * @return
     */
    boolean checkLogin(String uid);

    /**
     * 验证身份
     * @param uid 用户id
     * @param token 登录发放的token
     * @return
     */
    boolean checkUser(String uid,String token);


}
