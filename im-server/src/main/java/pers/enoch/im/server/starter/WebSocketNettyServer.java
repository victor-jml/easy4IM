package pers.enoch.im.server.starter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.enoch.im.server.init.ServerHandlerInitializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @Author yang.zhao
 * @Date 2020/12/2 10:23
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Component
public class WebSocketNettyServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Value("${server.netty.port}")
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
                .childHandler(new ServerHandlerInitializer());
        ChannelFuture future = serverBootstrap.bind().sync();
        if(future.isSuccess()){
            log.info("ws server 启动成功，绑定端口号为： " + port);
        }
    }

    @PreDestroy
    public void destroy(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("关闭 ws server 成功");
    }

}
