package pers.enoch.im.server.task;

import io.netty.channel.Channel;
import pers.enoch.im.common.protobuf.Single;
import pers.enoch.im.server.service.SingleChatService;
import pers.enoch.im.server.util.SessionUtil;


/**
 * @Author yang.zhao
 * @Date 2020/12/15 16:50
 * @Version 1.0
 * @Description 私聊业务
 **/
public class SingleChatMsgTask implements Runnable{
    private final SingleChatService singleChatService;

    private Channel channel;

    private Single.SingleSendRequest request;

    public SingleChatMsgTask(Channel channel, Single.SingleSendRequest request, SingleChatService singleChatService){
        this.channel = channel;
        this.request = request;
        this.singleChatService = singleChatService;
    }

    @Override
    public void run() {
        if(SessionUtil.hasLogin(request.getTo())){
            Channel receive = SessionUtil.getChannel(request.getTo());
            singleChatService.sendMsgSingle(channel,receive,request);
        }

    }
}
