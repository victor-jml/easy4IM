package pers.enoch.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pers.enoch.im.common.protobuf.Auth;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final Log logger = LogFactory.getLog(ClientHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("start connect");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info(msg);
        logger.info("connect success");
        Auth.AuthRequest request = Auth.AuthRequest.newBuilder()
                .setUid("enoch")
                .setToken("6dbef21518e44bb1ae31fd791e96645a")
                .build();
        ctx.channel().writeAndFlush(request);
    }
}
