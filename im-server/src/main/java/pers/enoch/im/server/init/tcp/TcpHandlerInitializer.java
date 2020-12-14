package pers.enoch.im.server.init.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import pers.enoch.im.server.handler.Authhandler;
import pers.enoch.im.server.handler.ChatHandler;

/**
 * @Author yang.zhao
 * @Date 2020/12/2 10:09
 * @Version 1.0
 * @Description
 **/
public class TcpHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Value("${server.netty.debug}")
    private boolean debug;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if(debug){
            // 可以打印出报文的请求和响应细节
            pipeline.addLast(new LoggingHandler());
        }
        // 解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // protobuf解析器
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        // 添加认证的handler
        pipeline.addLast(Authhandler.getInstance());
        // 添加业务逻辑handler
        pipeline.addLast(ChatHandler.getInstance());
    }
}
