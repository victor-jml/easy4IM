package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.attribute.Attributes;
import pers.enoch.im.session.Session;

import java.util.UUID;

/**
 * @Author yang.zhao
 * @Date 2020/12/1 16:03
 * @Version 1.0
 * @Description
 **/
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    public static final WebSocketHandler INSTANCE = new WebSocketHandler();

    private WebSocketHandler(){

    }

    private static ChannelGroup channelGroup;
    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.info("server receive message :" + textWebSocketFrame.text());
        sendMessage(channelHandlerContext);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info( ctx.name() + "与客户端建立连接");
        String userId = UUID.randomUUID().toString();

        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info( ctx.name() + "与客户端断开连接");
        channelGroup.remove(ctx.channel());
    }

    public void sendMessage(ChannelHandlerContext ctx){
        String message = "hello from server," + ctx.channel().localAddress() + "send message";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }
}
