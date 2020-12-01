package pers.enoch.im.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Value;
import pers.enoch.im.server.handler.WebSocketHandler;

/**
 * @Author yang.zhao
 * @Date 2020/11/30 16:22
 * @Version 1.0
 * @Description: Handler初始化
 **/

public class ServerHandlerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Value("${server.netty.debug}")
    private boolean debug;

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline pipeline = nioSocketChannel.pipeline();
        if(debug){
            // 可以打印出报文的请求和响应细节
            pipeline.addLast(new LoggingHandler());
        }
        // 添加HttpServer解析
        pipeline.addLast(new HttpServerCodec());
        // 添加参数对象解析
        pipeline.addLast(new HttpObjectAggregator(65536));
        // 添加大数据解析
        pipeline.addLast(new ChunkedWriteHandler());
        // 添加WebSocket数据压缩
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // 添加WebSocket协议配置，设置访问路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/login",null,true));

//        // 解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
//        pipeline.addLast(new ProtobufVarint32FrameDecoder());
//        // 这里加入与服务端约定的对象进行解析
//
//        // protobuf解析器
//        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
//
//        // 添加WebSocket协议包解码
//
//        // 添加WebSocket协议包编码
//
//        // protobuf编码器
//        pipeline.addLast(new ProtobufEncoder());

        // 自定义handler
        pipeline.addLast(WebSocketHandler.INSTANCE);
    }
}
