package pers.enoch.im.server.service;

import io.netty.channel.Channel;
import pers.enoch.im.api.mapper.OfflineMsgMapper;
import pers.enoch.im.api.mapper.SendMsgMapper;
import pers.enoch.im.api.model.OfflineMsg;
import pers.enoch.im.api.model.SendMsg;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.protobuf.Single;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public class SingleChatService {
    @Resource
    private UserStatusService userStatusService;
    @Resource
    private SendMsgMapper sendMsgMapper;
    @Resource
    private OfflineMsgMapper offlineMsgMapper;


    public void sendMsgSingle(Channel send , Channel receive, Single.SingleSendRequest singleSendRequest) {
        // check user isOnline
        boolean isLogin = userStatusService.checkLogin(singleSendRequest.getTo());

        // message insert dataBase
        SendMsg msg = generateSendMsg(singleSendRequest);
        sendMsgMapper.insert(msg);
        OfflineMsg offlineMsg = generateOfflineMsg(singleSendRequest);
        offlineMsgMapper.insert(offlineMsg);

        // if(Online) send - check Ack
        if(isLogin){
            Single.SinglePushRequest pushMsg = Single.SinglePushRequest.newBuilder()
                    .setMsgId(offlineMsg.getMsgId())
                    .setTimestamp(System.currentTimeMillis())
                    .setType(offlineMsg.getMsgType())
                    .setContent(offlineMsg.getMsgContent())
                    .setFrom(offlineMsg.getMsgFrom())
                    .build();
            receive.writeAndFlush(pushMsg);
        }
        Single.SingleSendResponse response = Single.SingleSendResponse.newBuilder()
                .setMsgId(offlineMsg.getMsgId())
                .setTimestamp(System.currentTimeMillis())
                .build();
        send.writeAndFlush(response);

    }

    private SendMsg generateSendMsg(Single.SingleSendRequest singleSendRequest){
        return SendMsg.builder()
                .msgFrom(singleSendRequest.getFrom())
                .msgTo(singleSendRequest.getTo())
                .groupId(0L)
                .msgType(singleSendRequest.getType())
                .sendTime(new Date())
                .msgContent(singleSendRequest.getContent())
                .msgSeq(0)
                .build();
    }

    private OfflineMsg generateOfflineMsg(Single.SingleSendRequest singleSendRequest){
        return OfflineMsg.builder()
                .msgFrom(singleSendRequest.getFrom())
                .msgTo(singleSendRequest.getTo())
                .groupId(0L)
                .msgType(singleSendRequest.getType())
                .sendTime(new Date())
                .msgContent(singleSendRequest.getContent())
                .msgSeq(0)
                .delivered(0)
                .build();
    }
}
