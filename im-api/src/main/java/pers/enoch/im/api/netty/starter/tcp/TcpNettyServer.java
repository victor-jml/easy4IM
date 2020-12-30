package pers.enoch.im.api.netty.starter.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.enoch.im.api.netty.init.tcp.TcpHandlerInitializer;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description:
 **/
@Component
@Slf4j
public class TcpNettyServer {

    private final int port = 9000;

    private static class SingletonHolder{
        static final TcpNettyServer INSTANCE = new TcpNettyServer();
    }

    public static TcpNettyServer getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private ServerBootstrap server;

    private ChannelFuture future;

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
        this.future = server.bind(port);

        log.info("tcp server start success.  bind port :  {} ", port);
    }

    public static void main(String[] args) throws InterruptedException {
        TcpNettyServer tcpNettyServer = new TcpNettyServer();
        tcpNettyServer.start();
    }

}
