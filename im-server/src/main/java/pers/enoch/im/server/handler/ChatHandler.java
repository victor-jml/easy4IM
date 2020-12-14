package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.exception.IMException;
import pers.enoch.im.common.generate.Single;

/**
 * @Author yang.zhao
 * @Date 2020/12/14 14:36
 * @Version 1.0
 * @Description
 **/
@ChannelHandler.Sharable
@Slf4j
public class ChatHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private UserStatusService userStatusService;

    private static class ChatHandlerHolder{
        private static final ChatHandler INSTANCE = new ChatHandler();
    }

    public static ChatHandler getInstance(){
        return ChatHandlerHolder.INSTANCE;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 单聊
        if(msg instanceof Single.SingleSendRequest){

        }else {
            // todo 其他指令
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        userStatusService.offline(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 心跳包
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleEvent.state() == IdleState.READER_IDLE) {
                ctx.channel().close();
                // 写空闲
            } else if (idleEvent.state() == IdleState.WRITER_IDLE) {
                // 读写空闲
            } else if (idleEvent.state() == IdleState.ALL_IDLE) {

            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(IMException.isResetByPeer(cause.getMessage())){
            return;
        }
        log.error(cause.getMessage(),cause);
    }
}
