package pers.enoch.im.server.starter.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.enoch.im.server.init.tcp.TcpHandlerInitializer;

import javax.annotation.PreDestroy;

/**
 * @Author yang.zhao
 * @Date 2020/12/14 12:47
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Component
public class TcpNettyServer {

    private final int port = 9000;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private ServerBootstrap server;

    private ChannelFuture future;

    private static class SingletonHolder{
        static final TcpNettyServer INSTANCE = new TcpNettyServer();
    }

    public static TcpNettyServer getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public TcpNettyServer(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1000)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new TcpHandlerInitializer());
    }

    public void start() throws InterruptedException {
        future = server.bind(9000).sync();
        if(future.isSuccess()){
            log.info("tcp server 启动成功，绑定端口号为： " + port);
        }
    }

//    public void init() throws InterruptedException {
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        serverBootstrap.group(bossGroup,workerGroup)
//                .channel(NioServerSocketChannel.class)
//                //设置服务器端口
//                .localAddress(new InetSocketAddress(port))
//                // TCP长连接
//                .option(ChannelOption.SO_BACKLOG,1000)
//                .childOption(ChannelOption.SO_KEEPALIVE,true)
//                .childHandler(new TcpHandlerInitializer());
//        ChannelFuture future = serverBootstrap.bind().sync();
//        if(future.isSuccess()){
//            log.info("tcp server 启动成功，绑定端口号为： " + port);
//        }
//    }

    @PreDestroy
    public void destroy(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("关闭 tcp server 成功");
    }

}
