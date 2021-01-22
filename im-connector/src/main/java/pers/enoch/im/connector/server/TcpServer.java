package pers.enoch.im.connector.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.enoch.im.connector.decoder.MessageDecoder;
import pers.enoch.im.connector.encode.MessageEncoder;
import pers.enoch.im.connector.handle.ImHandler;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description: TCp Server (Connect to User)
 **/
@Component
@Slf4j
public class TcpServer {
    @Value("${tcp.port}")
    private int port;


    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * ServerBootstrap 是一个启动NIO服务的辅助启动类
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            /**
             * 设置group，将bossGroup， workerGroup线程组传递到ServerBootstrap
             */
            serverBootstrap = serverBootstrap.group(bossGroup, workerGroup);
            /**
             * ServerSocketChannel是以NIO的selector为基础进行实现的，用来接收新的连接，这里告诉Channel通过NioServerSocketChannel获取新的连接
             */
            serverBootstrap = serverBootstrap.channel(NioServerSocketChannel.class);
            /**
             * option是设置 bossGroup，childOption是设置workerGroup netty 默认数据包传输大小为1024字节, 设置它可以自动调整下一次缓冲区建立时分配的空间大小，避免内存的浪费
             * 最小 初始化 最大 (根据生产环境实际情况来定) 使用对象池，重用缓冲区
             */
            // 添加handler，管道中的处理器，通过ChannelInitializer来构造
            serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    // 客户端分钟没有消息过来就认为挂了
                    pipeline.addLast(new IdleStateHandler(600, 0, 0));
                    pipeline.addLast(new MessageDecoder());
                    pipeline.addLast(new MessageEncoder());
                    pipeline.addLast(ImHandler.getInstance());
                }
            });

            // 6.设置参数
            // 设置参数，TCP参数
            // 连接缓冲池的大小
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 4096);
            // 维持链接的活跃，清除死链接
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 关闭延迟发送
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            log.warn("tcp服务启动 , 端口号 {}" , port);
            /**
             * 绑定端口，同步等待成功
             */
            ChannelFuture f = serverBootstrap.bind(port).sync();
            /**
             * 等待服务器监听端口关闭
             */
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {

        } finally {
            log.warn("tcp服务关闭");

            /**
             * 退出，释放线程池资源
             */
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
