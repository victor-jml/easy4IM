package pers.enoch.im.server.starter.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.enoch.im.server.init.tcp.TcpHandlerInitializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @Author yang.zhao
 * @Date 2020/12/14 12:47
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Component
public class TcpNettyServer {
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Value("${server.netty.TcpPort}")
    private int port;

    @PostConstruct
    public void init() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                //设置服务器端口
                .localAddress(new InetSocketAddress(port))
                // TCP长连接
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new TcpHandlerInitializer());
        ChannelFuture future = serverBootstrap.bind().sync();
        if(future.isSuccess()){
            log.info("tcp server 启动成功，绑定端口号为： " + port);
        }
    }

    @PreDestroy
    public void destroy(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("关闭 tcp server 成功");
    }

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                //设置服务器端口
                .localAddress(new InetSocketAddress(19999))
                // TCP长连接
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new TcpHandlerInitializer());
        ChannelFuture future = serverBootstrap.bind().sync();
        if(future.isSuccess()){
            log.info("tcp server 启动成功，绑定端口号为：{} " + 9000);
        }
    }
}
