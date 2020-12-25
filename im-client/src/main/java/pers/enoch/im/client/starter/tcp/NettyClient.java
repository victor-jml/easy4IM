package pers.enoch.im.client.starter.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pers.enoch.im.client.handler.LogicClientHandler;
import pers.enoch.im.client.handler.MessageDecoder;
import pers.enoch.im.client.handler.MessageEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public class NettyClient {
	private final Log logger = LogFactory.getLog(NettyClient.class);

	private final static String HOST = "127.0.0.1";
	private final static int PORT = 9000;

	private final static int READER_IDLE_TIME_SECONDS = 20;
	private final static int WRITER_IDLE_TIME_SECONDS = 20;
	private final static int ALL_IDLE_TIME_SECONDS = 40;

	private EventLoopGroup loop = new NioEventLoopGroup();

	public static void main(String[] args) throws Exception {
		NettyClient client = new NettyClient();
		client.run();
	}

	public void run() throws Exception {
		try {
			doConnect(new Bootstrap(), loop);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * netty client 连接，连接失败10秒后重试连接
	 */
	public Bootstrap doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
		try {
			if (bootstrap != null) {
				bootstrap.group(eventLoopGroup);
				bootstrap.channel(NioSocketChannel.class);
				bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
				bootstrap.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();

						p.addLast("idleStateHandler", new IdleStateHandler(READER_IDLE_TIME_SECONDS
								, WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS, TimeUnit.SECONDS));
//
//
//						p.addLast(new ProtobufVarint32FrameDecoder());
//
//
//						p.addLast(new ProtobufVarint32LengthFieldPrepender());
//						p.addLast(new ProtobufEncoder());

						p.addLast(new MessageDecoder());
						p.addLast(new MessageEncoder());

						p.addLast("clientHandler", new LogicClientHandler());
					}
				});
				bootstrap.remoteAddress(HOST, PORT);
				ChannelFuture f = bootstrap.connect().addListener((ChannelFuture futureListener)->{
					final EventLoop eventLoop = futureListener.channel().eventLoop();
					if (!futureListener.isSuccess()) {
						logger.warn("Failed to connect to server, try connect after 10s");
						futureListener.channel().eventLoop().schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
					}
				});
				f.channel().closeFuture().sync();
				eventLoopGroup.shutdownGracefully();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bootstrap;
	}
}
