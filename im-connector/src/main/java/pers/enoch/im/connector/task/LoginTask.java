package pers.enoch.im.connector.task;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.model.SendMsg;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;
import pers.enoch.im.common.utils.LoginUtil;
import pers.enoch.im.common.utils.RedisUtil;
import pers.enoch.im.connector.SpringBeanUtil;
import pers.enoch.im.connector.common.UserChannelCache;
import pers.enoch.im.connector.service.OfflineService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
@Slf4j
public class LoginTask implements Runnable  {

    private Channel channel;

    private Status.Request request;

    private final OfflineService offlineService;

    public LoginTask(Status.Request request, Channel channel){
        this.channel = channel;
        this.request = request;
        this.offlineService = SpringBeanUtil.getBean(OfflineService.class);
    }

    @Override
    public void run() {
        log.info("receive client {}  login message",request.getUserId());
        if(!LoginUtil.checkToken(request.getUserId(),request.getToken())){
            sendErrorToClient(channel);
        }else {
            UserChannelCache.set(request.getUserId(),channel);
            log.info("user : {} login success ", request.getUserId());
            sendAckToClient(channel);
        }

    }



    /**
     * login token check failed
     * @param channel
     */
    private void sendErrorToClient(Channel channel){
        Status.Response response = Status.Response.newBuilder()
                .setStatusCode(ResultEnum.USER_TOKEN_EXPIRE.getCode())
                .setStatusMsg(ResultEnum.USER_TOKEN_EXPIRE.getMessage())
                .build();
        channel.writeAndFlush(response);
    }

    /**
     * login success
     * @param channel
     */
    private void sendAckToClient(Channel channel){
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
    private void pushMsg(Channel channel, List<SendMsg> offlineMsgs){
        List<Msg.SendMsg> sendMsgs = offlineMsgs.stream().map(offlineMsg -> {
            return Msg.SendMsg.newBuilder()
                    .setMsgId(offlineMsg.getMsgId())
                    .setTimestamp(offlineMsg.getSendTime().getTime())
                    .setSender(offlineMsg.getSenderId())
                    .setReceiver(offlineMsg.getReceiverId())
                    .setContent(offlineMsg.getMsgContent())
                    .setMsgType(Msg.SendMsg.MsgType.FILE)
                    .setReceiveType(Msg.SendMsg.ReceiveType.GROUP)
                    .build();
        }).collect(Collectors.toList());
        sendMsgs.forEach((channel::writeAndFlush));
    }
}
