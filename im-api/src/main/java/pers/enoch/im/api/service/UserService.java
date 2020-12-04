package pers.enoch.im.api.service;


/**
 * @author yang.zhao
 */
public interface UserService {

    /**
     * 验证身份
     * @param uid 用户id
     * @param token 登录发放的token
     * @return
     */
    boolean checkUser(String uid,String token);
}
