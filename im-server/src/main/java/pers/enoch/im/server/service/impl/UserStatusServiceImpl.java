package pers.enoch.im.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.utils.LoginUtil;
import pers.enoch.im.common.utils.RedisUtil;
import pers.enoch.im.server.service.UserStatusService;

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
    public boolean checkToken(String uid, String token) {
        return LoginUtil.checkToken(uid, token);
    }

    @Override
    public boolean checkToken(String uid, Long oldTimestamp) {
        Object o = RedisUtil.get(Constant.REDIS_USER_PREFIX,uid);
        if(o == null){
            return false;
        }
        String token = (String)o;
        String timestamp = token.split(",")[1];
        return oldTimestamp != Long.parseLong(timestamp);
    }


}
