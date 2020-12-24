package pers.enoch.im.server.task;

import io.netty.channel.Channel;
import pers.enoch.im.api.service.MsgService;
import pers.enoch.im.api.service.impl.MsgServiceImpl;
import pers.enoch.im.common.protobuf.Single;
import pers.enoch.im.server.util.SessionUtil;


/**
 * @Author yang.zhao
 * @Date 2020/12/15 16:50
 * @Version 1.0
 * @Description 私聊业务
 **/
public class SingleChatMsgTask implements Runnable{
    private final MsgService msgService;

    private Channel channel;

    private Single.SingleSendRequest request;

    public SingleChatMsgTask(Channel channel, Single.SingleSendRequest request, MsgServiceImpl msgService){
        this.channel = channel;
        this.request = request;
        this.msgService = msgService;
    }

    @Override
    public void run() {
        if(SessionUtil.hasLogin(request.getTo())){
            Channel receive = SessionUtil.getChannel(request.getTo());

        }
        msgService.sendMsgSingle(channel,receive,request);
    }
}
