package pers.enoch.im.api.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.enoch.im.api.netty.task.AuthMsgTask;
import pers.enoch.im.api.netty.task.LogoutMsgTask;
import pers.enoch.im.api.netty.task.SingleChatMsgTask;
import pers.enoch.im.api.netty.task.execute.TaskExecute;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.exception.IMException;
import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.KeepAlive;
import pers.enoch.im.common.protobuf.Logout;
import pers.enoch.im.common.protobuf.Single;


/**
 * @Author yang.zhao
 * @Date 2020/12/14 14:36
 * @Version 1.0
 * @Description
 **/
@ChannelHandler.Sharable
@Slf4j
public class ImHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private UserStatusService userStatusService;

    private static class ChatHandlerHolder{
        private static final ImHandler INSTANCE = new ImHandler();
    }

    public static ImHandler getInstance(){
        return ChatHandlerHolder.INSTANCE;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("接收到客户端消息");
        // 心跳包
        if(msg instanceof KeepAlive.KeepAliveReq){
            KeepAlive.KeepAliveReq ping = (KeepAlive.KeepAliveReq)msg;
            // 先判断是否已登录，未登录的话直接关闭通道
            if(SessionUtil.getUserId(ctx.channel()) == null){
                ctx.channel().close();
            }
            log.info("server keepAlive heartbeat");
            KeepAlive.KeepAliveRes pong = KeepAlive.KeepAliveRes.newBuilder().build();
            ctx.writeAndFlush(pong);
        }else if(msg instanceof Auth.AuthRequest){
            // 验证登录
            Auth.AuthRequest authRequest = (Auth.AuthRequest)msg;
            log.info("{} connect to server", authRequest.getUid());
            AuthMsgTask authMsgTask = new AuthMsgTask(ctx.channel(), authRequest);
            TaskExecute.execute(authMsgTask);
        }else if(msg instanceof Logout.LogoutRequest){
            // 注销登录
            Logout.LogoutRequest logoutRequest = (Logout.LogoutRequest)msg;
            TaskExecute.execute(new LogoutMsgTask(ctx.channel(),logoutRequest));
        }else  {
            // 私聊业务
            Single.SingleSendRequest sendRequest = (Single.SingleSendRequest)msg;
            log.info("{}  send message to {}",sendRequest.getFrom(),sendRequest.getTo());
            TaskExecute.execute(new SingleChatMsgTask(ctx.channel(),sendRequest));
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 心跳包
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleEvent.state() == IdleState.READER_IDLE) {
                log.error("未收到客户端心跳包，断开连接");
                ctx.channel().close();
                SessionUtil.unBindSession(ctx.channel());
            }   // 写空闲
            else if (idleEvent.state() == IdleState.WRITER_IDLE) {

            }  // 读写空闲
            else if (idleEvent.state() == IdleState.ALL_IDLE) {

            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(IMException.isResetByPeer(cause.getMessage())){
            ctx.channel().close();
            SessionUtil.unBindSession(ctx.channel());
            return;
        }
        log.error(cause.getMessage(),cause);
    }
}