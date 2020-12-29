package pers.enoch.im.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.client.starter.tcp.NettyClient;
import pers.enoch.im.common.protobuf.KeepAlive;

import java.util.concurrent.TimeUnit;

/**
 * @Author yang.zhao
 * Date: 2020/12/29
 * Description:
 **/
@Slf4j
@ChannelHandler.Sharable
public class HeartBeatHandler extends SimpleChannelInboundHandler<KeepAlive.KeepAliveRes> {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("trigger client heartbeat send");
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.ALL_IDLE){
                KeepAlive.KeepAliveReq keepAliveReq = KeepAlive.KeepAliveReq.newBuilder().build();
                ctx.writeAndFlush(keepAliveReq);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, KeepAlive.KeepAliveRes keepAliveRes) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("disconnected ! try to connect 10 seconds later");
        ctx.channel().eventLoop().schedule((Runnable) NettyClient::new,10, TimeUnit.SECONDS);
    }
}
