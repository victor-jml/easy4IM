package pers.enoch.im.api.netty.task;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.api.SpringBeanUtil;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.protobuf.Status;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Slf4j
public class LoginTask implements Runnable{

    private UserStatusService userStatusService;

    private Channel channel;

    private Status.Request request;

    public LoginTask(Channel channel, Status.Request request){
        this.channel = channel;
        this.request = request;
        this.userStatusService = SpringBeanUtil.getBean(UserStatusService.class);
    }

    @Override
    public void run() {
        // check userToken
        boolean isLogin = userStatusService.checkToken(request.getUserId(), request.getToken());
        // login ack
        Status.Response response;
        if(!isLogin){
            log.error("user : {} login error !!! ," ,request.getUserId());
            response = Status.Response.newBuilder()
                    .setStatusCode(ResultEnum.USER_TOKEN_EXPIRE.getCode())
                    .setStatusMsg(ResultEnum.USER_TOKEN_EXPIRE.getMessage())
                    .build();
        }else {
            SessionUtil.bindSession(request.getUserId(),channel);
            log.info("user : {} login success ", request.getUserId());
            response = Status.Response.newBuilder()
                    .setStatusCode(ResultEnum.USER_LOGIN_SUCCESS.getCode())
                    .setStatusMsg(ResultEnum.USER_LOGIN_SUCCESS.getMessage())
                    .build();
        }
       channel.writeAndFlush(response);
    }
}
