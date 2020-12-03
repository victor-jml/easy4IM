package pers.enoch.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.constant.Constant;
import pers.enoch.im.common.constant.ReqTypeConstant;
import pers.enoch.im.common.generate.RequestOuterClass;
import pers.enoch.im.server.session.Session;


/**
 * @Author yang.zhao
 * @Date 2020/12/3 10:53
 * @Version 1.0
 * @Description 业务逻辑处理
 **/
@Slf4j
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<RequestOuterClass.Request> {

    public static final ChatHandler INSTANCE = new ChatHandler();

    private ChatHandler(){

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn(" {} 连接关闭", ctx.channel().id());
        userOffLine(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if(idleStateEvent.state() == IdleState.READER_IDLE){
                userOffLine(ctx);
            }
        }
        super.userEventTriggered(ctx,evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (Constant.CONNECTION_RESET_BY_PEER.equals(cause.getMessage())) {
            log.error("连接出现问题");
            return;
        }

        log.error(cause.getMessage(), cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestOuterClass.Request request) throws Exception {
        String sid = request.getSid();
        long uid = request.getUid();
        Integer type = request.getType();
        switch (type){
            case ReqTypeConstant.LOGIN:
                log.info("用户登录");
                userLogin(channelHandlerContext,uid,sid);
                break;
            case ReqTypeConstant.PING:
                log.info("客户端心跳");
                break;
            default:
                log.info("未知类型");
        }
    }

    /**
     * 用户登录
     * @param ctx
     * @param uid 登录id
     * @param sid 登录token
     */
    private void userLogin(ChannelHandlerContext ctx,Long uid,String sid){
        // todo 用户登录逻辑
    }

    /**
     * 用户离线
     * @param ctx
     */
    private void userOffLine(ChannelHandlerContext ctx){
        Session.remove(ctx.channel());
        ctx.channel().close();
    }
}
