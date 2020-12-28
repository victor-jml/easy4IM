package pers.enoch.im.client.starter.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.enoch.im.client.handler.ChatHandler;
import pers.enoch.im.client.handler.LogicClientHandler;
import pers.enoch.im.client.handler.MessageDecoder;
import pers.enoch.im.client.handler.MessageEncoder;
import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.Single;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
@Slf4j
@Component
public class NettyClient {
	private static class SingletonHolder{
		static final NettyClient INSTANCE = new NettyClient();
	}

	public static NettyClient getInstance(){
		return SingletonHolder.INSTANCE;
	}


	private EventLoopGroup workerGroup;

	private Bootstrap bootstrap;

	private ChannelFuture future;

	public NettyClient(){
		workerGroup = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		bootstrap.group(workerGroup)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG,1000)
				.option(ChannelOption.SO_KEEPALIVE,true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast(new MessageDecoder());
						pipeline.addLast(new MessageEncoder());
						pipeline.addLast(new LogicClientHandler());
						pipeline.addLast(new ChatHandler());
					}
				});
		try {
			future = bootstrap.connect("127.0.0.1",9000).sync();
			if(future.isSuccess()){
				log.info("connect to server");
			}
		} catch (InterruptedException e) {
			log.error("failed to connect to server");
			e.printStackTrace();
		}
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
