package pers.enoch.im.api.service;


import io.netty.channel.Channel;

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

    /**
     * 标记用户上线
     * @param uid
     * @param channel
     */
    void online(String uid, Channel channel);
}
