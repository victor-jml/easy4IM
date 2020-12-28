package pers.enoch.im.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.protobuf.Single;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description:
 **/
@ChannelHandler.Sharable
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<Single.SinglePushRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Single.SinglePushRequest singlePushRequest) throws Exception {
        String from = singlePushRequest.getFrom();
        log.info("receive message from : {} , message content : {} ", singlePushRequest.getFrom(),singlePushRequest.getContent());
    }
}
