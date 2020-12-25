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
public class LogicClientHandler extends ChannelInboundHandlerAdapter {
	private final Log logger = LogFactory.getLog(LogicClientHandler.class);
	private final static String CLIENTID = "123456789";


	@Override  
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("start connect");
		Auth.AuthRequest request = Auth.AuthRequest.newBuilder()
					.setUid("enoch")
					.setToken("7c519bcb88a94f03bf9de16aed7299a4")
					.build();
		ctx.channel().writeAndFlush(request);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.debug("连接断开 ");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("connect success");
		if(msg instanceof Auth.AuthResponse){
			Auth.AuthResponse response = (Auth.AuthResponse) msg;
			logger.info("receive server message : " + response.toString());
		}
	}
}
