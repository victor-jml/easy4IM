package pers.enoch.im.connector.handle;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.exception.IMException;
import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;
import pers.enoch.im.connector.common.UserChannelCache;
import pers.enoch.im.connector.task.LoginTask;
import pers.enoch.im.connector.task.execute.TaskExecute;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
@ChannelHandler.Sharable
@Slf4j
@Component
public class ImHandler extends ChannelInboundHandlerAdapter {

    private static class ChatHandlerHolder{
        private static final ImHandler INSTANCE = new ImHandler();
    }

    public static ImHandler getInstance(){
        return ChatHandlerHolder.INSTANCE;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("接收到客户端消息");
        if(msg instanceof Status.Request){
            Status.Request request = (Status.Request)msg;
            if(request.getType().equals(Status.Request.Type.HEARTBEAT)){
                log.info("client : {}  heartbeat",request.getUserId());
                Status.Response response = Status.Response.newBuilder()
                        .setStatusCode(ResultEnum.HEART_BEAT.getCode())
                        .setStatusMsg(ResultEnum.HEART_BEAT.getMessage())
                        .build();
                ctx.channel().writeAndFlush(response);
            }else {
                log.info("client : {}  login",request.getUserId());
                TaskExecute.execute(new LoginTask(ctx.channel(),request));
            }
        }
        else if(msg instanceof Msg.SendMsg){
            Msg.SendMsg sendMsg = (Msg.SendMsg)msg;
            if(sendMsg.getReceiveType().equals(Msg.SendMsg.ReceiveType.SINGLE)){
                // single chat
                log.info("client : {}  send msg to : {} ",sendMsg.getSender(),sendMsg.getReceiver());
                TaskExecute.execute(new ChatTask(singleMsgServiceImpl,ctx.channel(),sendMsg));
            }else {
                // group chat
                log.info("client : {} send msg to group : {}",sendMsg.getSender(),sendMsg.getReceiver());
                TaskExecute.execute(new ChatTask(groupMsgServiceImpl,ctx.channel(),sendMsg));
            }
        }else if(msg instanceof Ack.AckMsg){
            Ack.AckMsg ackMsg = (Ack.AckMsg)msg;
            log.info("receive ack from client : {}",ackMsg.getReceiver());
            TaskExecute.execute(new AckTask(ctx.channel(),ackMsg));
        }else {
            // XXX todo : other type message
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        UserChannelCache.removeChannel(ctx.channel());
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.warn("10 mins not receive client heartbeat ，close channel");
                UserChannelCache.removeChannel(ctx.channel());
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(IMException.isResetByPeer(cause.getMessage())){
            ctx.channel().close();
            UserChannelCache.removeChannel(ctx.channel());
            return;
        }
        log.error(cause.getMessage(),cause);
    }
}