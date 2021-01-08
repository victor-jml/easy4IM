package pers.enoch.im.client.handler;


import cn.hutool.core.date.DateUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.client.starter.tcp.NettyClient;
import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
@Slf4j
@ChannelHandler.Sharable
public class ClientLogicHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof Status.Response){
			Status.Response response = (Status.Response)msg;
			log.info("client receive : {} , {}" , response.getStatusCode(),response.getStatusMsg());
		}else if(msg instanceof Ack.AckMsg){
			Ack.AckMsg ackMsg = (Ack.AckMsg)msg;
			log.info("{}  send message success , msgId : {}",ackMsg.getTimestamp(),ackMsg.getAckMsgId());
		}else if(msg instanceof Msg.SendMsg){
			Msg.SendMsg sendMsg = (Msg.SendMsg)msg;
			log.info("receive message from : {} , msgId : {} , time : {} , content : {}",
					sendMsg.getSender(),sendMsg.getMsgId(), DateUtil.format(new Date(sendMsg.getTimestamp()),"yyyy-MM-dd hh:mm:ss"),sendMsg.getContent());
			Ack.AckMsg ackMsg = Ack.AckMsg.newBuilder().setAckMsgId(sendMsg.getMsgId()).build();
			ctx.channel().writeAndFlush(ackMsg);
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		log.info("trigger client heartbeat send");
		if (evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;
			if (event.state()== IdleState.ALL_IDLE){
				Status.Request heartBeat = Status.Request.newBuilder()
						.setType(Status.Request.Type.HEARTBEAT)
						.build();
				ctx.writeAndFlush(heartBeat);
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("disconnected ! try to connect 10 seconds later");
		ctx.channel().eventLoop().schedule((Runnable) NettyClient::new,10, TimeUnit.SECONDS);
	}
}
