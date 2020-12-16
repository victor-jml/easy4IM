package pers.enoch.im.api.service.impl;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.api.utils.CacheUtil;
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
        LoginUtil.online(uid,token);

    }

    @Override
    public void offline(String uid) {
        LoginUtil.offline(uid);
    }

    @Override
    public void offline(ChannelHandlerContext ctx) {

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
        Object o = CacheUtil.get(uid);
        if(o == null){
            return false;
        }
        String token = (String)o;
        String timestamp = token.split(",")[1];
        return oldTimestamp != Long.parseLong(timestamp);
    }


}
