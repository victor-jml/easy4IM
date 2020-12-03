package pers.enoch.im.server.init;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import pers.enoch.im.common.generate.RequestOuterClass;
import pers.enoch.im.server.decode.WebSocketDecoder;
import pers.enoch.im.server.encode.WebSocketEncoder;
import pers.enoch.im.server.handler.ChatHandler;

/**
 * @Author yang.zhao
 * @Date 2020/12/2 10:09
 * @Version 1.0
 * @Description
 **/
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Value("${server.netty.debug}")
    private boolean debug;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if(debug){
            // 可以打印出报文的请求和响应细节
            pipeline.addLast(new LoggingHandler());
        }
        // 心跳连接，15秒客户端没有向服务器发送心跳则关闭连接
        pipeline.addLast(new IdleStateHandler(15,0,0));
        // websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast(new HttpServerCodec());
        // 以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        // netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(65536));
        // 添加WebSocket数据压缩
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // 添加WebSocket协议配置，设置访问路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true));


        // 解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // protobuf解析器
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        // 添加WebSocket协议包解码
        pipeline.addLast(WebSocketDecoder.INSTANCE);
        // 添加WebSocket协议包编码
        pipeline.addLast(WebSocketEncoder.INSTANCE);
        // protobuf编码器
        pipeline.addLast(new ProtobufDecoder(RequestOuterClass.Request.getDefaultInstance()));

        // 添加业务逻辑handler
        pipeline.addLast(new ChatHandler());
    }
}
