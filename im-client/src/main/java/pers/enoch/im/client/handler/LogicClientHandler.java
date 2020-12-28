package pers.enoch.im.client.handler;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.constant.ResultEnum;
import pers.enoch.im.common.protobuf.Auth;


/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
@Slf4j
@ChannelHandler.Sharable
public class LogicClientHandler extends SimpleChannelInboundHandler<Auth.AuthResponse> {



	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.debug("connect exception ");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("lost connect to server");
	}


	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Auth.AuthResponse authResponse) throws Exception {
		int status = authResponse.getStatus();
		if(status == ResultEnum.USER_LOGIN_SUCCESS.getCode()){
			log.info(ResultEnum.USER_LOGIN_SUCCESS.getMessage());
		}else {
			log.debug("user login failed");
		}
	}

}
