package pers.enoch.im.api.netty.task;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.MsgService;
import pers.enoch.im.common.protobuf.Msg;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Slf4j
public class ChatTask implements Runnable {

    private MsgService msgService;

    private Msg.SendMsg sendMsg;

    private Channel channel;

    public ChatTask(MsgService msgService,Channel channel,Msg.SendMsg sendMsg){
        this.channel = channel;
        this.sendMsg = sendMsg;
        this.msgService = msgService;

    }

    @Override
    public void run() {
        Channel sender = SessionUtil.getChannel(sendMsg.getSender());
        log.debug("sender original : {} , now : {}",sender.toString(),channel.toString());
        msgService.sendMsg(channel,sendMsg);

    }
}
