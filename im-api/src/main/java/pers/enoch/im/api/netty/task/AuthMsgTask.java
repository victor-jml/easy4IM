package pers.enoch.im.api.netty.task;

import io.netty.channel.Channel;
import pers.enoch.im.api.SpringBeanUtil;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.protobuf.Auth;


/**
 * @Author yang.zhao
 * @Date 2020/12/15 16:27
 * @Version 1.0
 * @Description 执行登录逻辑
 **/
public class AuthMsgTask implements Runnable{

    private UserStatusService userStatusService;

    private Auth.AuthRequest auth;
    private Channel channel;

    public AuthMsgTask(Channel channel,Auth.AuthRequest auth){
        this.auth = auth;
        this.channel = channel;
        userStatusService = SpringBeanUtil.getBean(UserStatusService.class);
    }

    @Override
    public void run() {

        boolean isLegal = userStatusService.checkToken(auth.getUid(), auth.getToken());
        Auth.AuthResponse response;
        if(!isLegal){
            response = Auth.AuthResponse.newBuilder()
                    .setErrCode(ResultEnum.USER_TOKEN_EXPIRE.getCode())
                    .setErrMsg(ResultEnum.USER_TOKEN_EXPIRE.getMessage())
                    .build();
        }else {
            // XXX 待优化，这里直接暴力将上一次登录的通道替换
            SessionUtil.bindSession(auth.getUid(),channel);
            response = Auth.AuthResponse.newBuilder()
                    .setStatus(ResultEnum.USER_LOGIN_SUCCESS.getCode())
                    .build();
        }
        channel.writeAndFlush(response);
    }
}
