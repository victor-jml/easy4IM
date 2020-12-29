package pers.enoch.im.client.starter.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.enoch.im.client.handler.*;
import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.Single;

import java.util.Date;
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
						pipeline.addLast(new HeartBeatHandler());
						pipeline.addLast(new LogicClientHandler());
						pipeline.addLast(new ChatHandler());
					}
				});

		doConnect(bootstrap, HOST, PORT, MAX_RETRY);
	}

	private void doConnect(Bootstrap bootstrap,String host,int port,int retry){
		future = bootstrap.connect(host,port).addListener(channelFuture -> {
			if (channelFuture.isSuccess()) {
				log.info("{} , connect success ",new Date());
				Channel channel = ((ChannelFuture) future).channel();
				send(channel);
			} else if (retry == 0) {
				log.error("reconnect times reach the maximum number, give up connecting");
				future.channel().close();
			} else {
				// order time to reconnect
				int order = (MAX_RETRY - retry) + 1;
				// reconnect time interval
				int delay = 1 << order;
				log.info("connect failed ,this is {} reconnect",order);
				bootstrap.config().group().schedule(() -> doConnect(bootstrap, host, port, retry - 1), delay, TimeUnit
						.SECONDS);
			}
		});
	}

	public boolean checkConnect(){
		return future != null && future.isSuccess();
	}

	public <T> void send(T message){
		Channel channel = future.channel();
		if(message instanceof Auth.AuthRequest){
			Auth.AuthRequest request = (Auth.AuthRequest)message;
			channel.writeAndFlush(request);
		}else if(message instanceof Single.SingleSendRequest){
			Single.SingleSendRequest request = (Single.SingleSendRequest)message;
			channel.writeAndFlush(request);
		}

	}


}
