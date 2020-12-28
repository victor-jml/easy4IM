package pers.enoch.im.api.netty.task;

import io.netty.channel.Channel;
import pers.enoch.im.api.SpringBeanUtil;
import pers.enoch.im.api.netty.service.SingleChatService;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.common.protobuf.Single;




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

    public SingleChatMsgTask(Channel channel, Single.SingleSendRequest request){
        this.channel = channel;
        this.request = request;
        this.singleChatService = SpringBeanUtil.getBean(SingleChatService.class);
    }

    @Override
    public void run() {
            Channel receive = SessionUtil.getChannel(request.getTo());
            singleChatService.sendMessage(channel,receive,request);
    }
}
