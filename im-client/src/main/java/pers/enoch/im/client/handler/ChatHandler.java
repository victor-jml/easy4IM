package pers.enoch.im.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.protobuf.Single;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description:
 **/
@ChannelHandler.Sharable
@Slf4j
public class ChatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof Single.SingleSendResponse){
            Single.SingleSendResponse response = (Single.SingleSendResponse)msg;
            log.info("receive message ACK : {} ", response.getMsgId());
        }else if(msg instanceof Single.SinglePushRequest){
            Single.SinglePushRequest request = (Single.SinglePushRequest)msg;
            log.info("receive message from: {} , content : {} ",request.getFrom(),request.getContent());
        }
    }
}
