package pers.enoch.im.api.netty.init.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import pers.enoch.im.api.netty.decode.MessageDecoder;
import pers.enoch.im.api.netty.encode.MessageEncoder;
import pers.enoch.im.api.netty.handler.ImHandler;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description:
 **/
public class TcpHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 可以打印出报文的请求和响应细节
        pipeline.addLast(new LoggingHandler());

        // 心跳连接，15秒客户端没有向服务器发送心跳则关闭连接
        pipeline.addLast(new IdleStateHandler(600,600,600));

        // 解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
//        pipeline.addLast(new ProtobufVarint32FrameDecoder());
//        pipeline.addLast(new ProtobufDecoder(Auth.AuthRequest.getDefaultInstance()));
//
//        protobuf解析器
//        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
//        pipeline.addLast(new ProtobufEncoder());

        pipeline.addLast(new MessageDecoder());
        pipeline.addLast(new MessageEncoder());

        // 添加业务逻辑handler
        pipeline.addLast(ImHandler.getInstance());
    }
}
