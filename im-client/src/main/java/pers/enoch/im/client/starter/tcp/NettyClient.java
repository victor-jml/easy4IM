package pers.enoch.im.client.starter.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.enoch.im.client.handler.ClientLogicHandler;
import pers.enoch.im.client.handler.MessageDecoder;
import pers.enoch.im.client.handler.MessageEncoder;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
@Slf4j
@Component
public class NettyClient {
	private final String HOST = "127.0.0.1";

	private final int PORT = 9000;

	private static boolean isLogin = false;

	private final int MAX_RETRY = 5;

	private Bootstrap bootstrap;

	private ChannelFuture future;

	public NettyClient(){
		bootstrap = new Bootstrap();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		bootstrap
				.group(workerGroup)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new MessageDecoder());
						pipeline.addLast(new MessageEncoder());
						pipeline.addLast(new IdleStateHandler(0,0,40));
						pipeline.addLast(new ClientLogicHandler());
					}
				});

		doConnect(bootstrap, HOST, PORT, MAX_RETRY);
	}

	private void doConnect(Bootstrap bootstrap,String host,int port,int retry){
		future = bootstrap.connect(host,port).addListener(channelFuture -> {
			if (channelFuture.isSuccess()) {
				log.info("{} , connect success ",new Date());
				Channel channel = ((ChannelFuture) future).channel();
				command();
			} else if (retry == 0) {
				log.error("reconnect times reach the maximum number, give up connecting");
				future.channel().close();
			} else {
				// order time to reconnect
				int order = (MAX_RETRY - retry) + 1;
				// reconnect time interval
				int delay = 1 << order;
				log.info("connect failed ,this is {} reconnect",order);
				bootstrap.config().group().schedule(() -> doConnect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
			}
		});
	}

	public boolean checkConnect(){
		return future != null && future.isSuccess();
	}

	public void command(){
		Scanner sc = new Scanner(System.in);
		new Thread(() -> {
			final String[] userId = new String[2];
			while (!Thread.interrupted()){
			if(!isLogin){
				System.out.println("输入账号:");
				userId[0] = sc.nextLine();
				System.out.println("输入token:");
				String token = sc.nextLine();
				Status.Request request = Status.Request.newBuilder()
						.setType(Status.Request.Type.LOGIN)
						.setUserId(userId[0])
						.setToken(token)
						.build();
				send(request);
				isLogin = true;
			}else{
				System.out.println("输入接收Id:");
				String receiver = sc.nextLine();
				System.out.println("输入消息:");
				String content = sc.nextLine();
				Msg.SendMsg sendMsg = Msg.SendMsg.newBuilder()
						.setTimestamp(System.currentTimeMillis())
						.setMsgType(Msg.SendMsg.MsgType.TEXT)
						.setReceiveType(Msg.SendMsg.ReceiveType.SINGLE)
						.setSender(userId[0])
						.setReceiver(receiver)
						.setContent(content)
						.build();
				send(sendMsg);
			}
			}
		}).start();
	}

	public <T> void send(T message){
		Channel channel = future.channel();
		if(message instanceof Status.Request){
			log.info("send Request success");
			Status.Request request = (Status.Request)message;
			channel.writeAndFlush(request);
		}else if(message instanceof Msg.SendMsg){
			log.info("send Message success");
			Msg.SendMsg sendMsg = (Msg.SendMsg)message;
			channel.writeAndFlush(sendMsg);
		}

	}


}
