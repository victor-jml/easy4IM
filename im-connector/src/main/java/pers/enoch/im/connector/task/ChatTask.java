package pers.enoch.im.connector.task;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.connector.common.UserChannelCache;
import pers.enoch.im.connector.service.SendMsgService;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Slf4j
public class ChatTask implements Runnable {

    private SendMsgService sendMsgService;

    private Msg.SendMsg sendMsg;

    private Channel channel;

    public ChatTask(SendMsgService sendMsgService, Channel channel, Msg.SendMsg sendMsg){
        this.channel = channel;
        this.sendMsg = sendMsg;
        this.sendMsgService = sendMsgService;

    }

    @Override
    public void run() {
        Channel sender = UserChannelCache.getChannel(sendMsg.getSender());
        log.debug("sender original : {} , now : {}",sender.toString(),channel.toString());
        sendMsgService.sendMsg(channel,sendMsg);

    }
}
