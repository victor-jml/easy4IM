package pers.enoch.im.api.service.impl;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.service.UserService;
import pers.enoch.im.api.utils.LoginUtil;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 16:43
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean checkUser(String uid, String token) {
        return LoginUtil.checkToken(Long.valueOf(uid), token);
    }

    @Override
    public void online(String uid, Channel channel) {
        // todo redis存入用户id与其对应的channel
    }
}
