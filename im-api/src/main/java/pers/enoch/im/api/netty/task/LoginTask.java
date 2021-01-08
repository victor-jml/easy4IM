package pers.enoch.im.api.netty.task;

import io.jsonwebtoken.lang.Collections;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.api.SpringBeanUtil;
import pers.enoch.im.api.model.OfflineMsg;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.OfflineService;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Slf4j
public class LoginTask implements Runnable{

    private final UserStatusService userStatusService;

    private final OfflineService offlineService;

    private Channel channel;

    private Status.Request request;

    public LoginTask(Channel channel, Status.Request request){
        this.channel = channel;
        this.request = request;
        this.userStatusService = SpringBeanUtil.getBean(UserStatusService.class);
        this.offlineService = SpringBeanUtil.getBean(OfflineService.class);
    }

    @Override
    public void run() {
        // check userToken
        boolean isLogin = userStatusService.checkToken(request.getUserId(), request.getToken());
        if(!isLogin){
            log.error("user : {} login error !!! ," ,request.getUserId());
            sendErrorToClient(channel);
        }else {
            SessionUtil.bindSession(request.getUserId(),channel);
            log.info("user : {} login success ", request.getUserId());
            sendAckToClient(channel);
        }
        // todo get user offline msg And push
        List<OfflineMsg> offlineMsgs = offlineService.pollOfflineMsg(request.getUserId());
        if(!Collections.isEmpty(offlineMsgs)){
            pushMsg(channel,offlineMsgs);
        }
    }


    private void sendAckToClient(Channel channel){
        Status.Response response = Status.Response.newBuilder()
                .setStatusCode(ResultEnum.USER_TOKEN_EXPIRE.getCode())
                .setStatusMsg(ResultEnum.USER_TOKEN_EXPIRE.getMessage())
                .build();
        channel.writeAndFlush(response);
    }

    private void sendErrorToClient(Channel channel){
        Status.Response response = Status.Response.newBuilder()
                .setStatusCode(ResultEnum.USER_LOGIN_SUCCESS.getCode())
                .setStatusMsg(ResultEnum.USER_LOGIN_SUCCESS.getMessage())
                .build();
        channel.writeAndFlush(response);
    }

    /**
     * push offline message to client
     * @param channel
     * @param offlineMsgs
     */
    private void pushMsg(Channel channel,List<OfflineMsg> offlineMsgs){
        List<Msg.SendMsg> sendMsgs = offlineMsgs.stream().map(offlineMsg -> {
            Msg.SendMsg msg = Msg.SendMsg.newBuilder()
                    .setMsgId(offlineMsg.getMsgId().toString())
                    .setSender(offlineMsg.getMsgFrom())
                    .setReceiver(offlineMsg.getMsgTo())
                    .setContent(offlineMsg.getMsgContent())
                    .setMsgType(offlineMsg.getMsgType() == 0 ? Msg.SendMsg.MsgType.TEXT : Msg.SendMsg.MsgType.FILE)
                    .setReceiveType(offlineMsg.getGroupId() == null ? Msg.SendMsg.ReceiveType.SINGLE : Msg.SendMsg.ReceiveType.GROUP)
                    .build();
            return msg;
        }).collect(Collectors.toList());
        sendMsgs.forEach((channel::writeAndFlush));
    }
}
