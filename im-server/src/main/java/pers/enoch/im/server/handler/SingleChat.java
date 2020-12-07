package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.generate.Single;

/**
 * @Author yang.zhao
 * @Date 2020/12/3 10:53
 * @Version 1.0
 * @Description 私聊业务逻辑处理
 **/
@Slf4j
@ChannelHandler.Sharable
public class SingleChat extends SimpleChannelInboundHandler<Single.SingleSendRequest> {

    private static class SingleHolder{
        private static final SingleChat INSTANCE = new SingleChat();
    }

    public static SingleChat getInstance(){
        return SingleHolder.INSTANCE;
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Single.SingleSendRequest singleSendRequest) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
