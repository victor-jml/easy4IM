package pers.enoch.im.api.service.impl;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.SendMsgMapper;
import pers.enoch.im.api.model.OfflineMsg;
import pers.enoch.im.api.model.SendMsg;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.OfflineService;
import pers.enoch.im.api.service.SendMsgService;
import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.common.protobuf.Msg;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Service
@Slf4j
public class SingleSendMsgServiceImpl implements SendMsgService {
    @Resource
    private SendMsgMapper sendMsgMapper;
    @Resource
    private OfflineService offlineService;

    @Override
    public void sendMsg(Channel channel, Msg.SendMsg sendMsg) {
        OfflineMsg offlineMsg = generateOfflineMsg(sendMsg);
        SendMsg msg = generateSendMsg(sendMsg);
        Long msgId = offlineService.saveOfflineMsg(offlineMsg);
        int insert = 0;
        try {
            insert = sendMsgMapper.insert(msg);
        } catch (Exception e) {
            log.error("insert sent-msg failed");
        }
        if(SessionUtil.hasLogin(sendMsg.getReceiver())){
            pushMsg(offlineMsg.getMsgId().toString(),sendMsg);
        }
        if(insert == 1){
            sendAck(msgId.toString(),sendMsg);
        };
    }

    private void pushMsg(String msgId,Msg.SendMsg sendMsg){
        Msg.SendMsg push = Msg.SendMsg.newBuilder(sendMsg)
                .setMsgId(msgId)
                .build();
        Channel channel = SessionUtil.getChannel(sendMsg.getReceiver());
        channel.writeAndFlush(push);
    }

    private void sendAck(String msgId,Msg.SendMsg sendMsg){
        Ack.AckMsg ack = Ack.AckMsg.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setAckMsgId(msgId)
                .build();
        Channel channel = SessionUtil.getChannel(sendMsg.getSender());
        channel.writeAndFlush(ack);
    }

    private OfflineMsg generateOfflineMsg(Msg.SendMsg sendMsg){
        return OfflineMsg.builder()
                .msgFrom(sendMsg.getSender())
                .msgTo(sendMsg.getReceiver())
                .msgContent(sendMsg.getContent())
                .delivered(0)
                .msgType( (sendMsg.getMsgType().equals(Msg.SendMsg.MsgType.TEXT)) ? 0 : 1)
                .sendTime(new Date())
                .build();
    }

    private SendMsg generateSendMsg(Msg.SendMsg sendMsg){
        return SendMsg.builder()
                .msgFrom(sendMsg.getSender())
                .msgTo(sendMsg.getReceiver())
                .msgContent(sendMsg.getContent())
                .msgType( (sendMsg.getMsgType().equals(Msg.SendMsg.MsgType.TEXT)) ? 0 : 1)
                .sendTime(new Date())
                .build();
    }
}
