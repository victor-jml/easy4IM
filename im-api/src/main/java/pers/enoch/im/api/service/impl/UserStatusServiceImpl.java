package pers.enoch.im.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.redis.UserInfoCache;

/**
 * @Author yang.zhao
 * Date: 2021/1/8
 * Description:
 **/
@Service
@Slf4j
public class UserStatusServiceImpl implements UserStatusService {

    @Override
    public void online(String uid, String token) {
        UserInfoCache.online(uid,token);
    }

    @Override
    public void offline(String uid) {
        UserInfoCache.offline(uid);
    }

    @Override
    public boolean checkLogin(String uid) {
        return UserInfoCache.checkLoginById(uid);
    }

    @Override
    public boolean checkToken(String uid, String token) {
        return UserInfoCache.checkToken(uid, token);
    }

}
