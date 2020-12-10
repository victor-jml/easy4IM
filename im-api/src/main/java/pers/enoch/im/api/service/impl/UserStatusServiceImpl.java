package pers.enoch.im.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.api.utils.LoginUtil;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 16:43
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Service
public class UserStatusServiceImpl implements UserStatusService {

    @Override
    public void online(String uid,String token) {
        // todo redis存入用户id与其对应的token
        LoginUtil.online(uid,token);
    }

    @Override
    public void offline(String uid) {
        LoginUtil.offline(uid);
    }

    @Override
    public boolean checkLogin(String uid) {
        return LoginUtil.checkLogin(uid);
    }

    @Override
    public boolean checkUser(String uid, String token) {
        return LoginUtil.checkToken(Long.valueOf(uid), token);
    }

}
