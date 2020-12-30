package pers.enoch.im.server.encode;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @Author yang.zhao
 * @Date 2020/12/3 10:36
 * @Version 1.0
 * @Description websocket编码
 **/
@Slf4j
@ChannelHandler.Sharable
public class WsEncoder extends MessageToMessageEncoder<MessageLiteOrBuilder> {

    private static class WebsocketEncodeHolder{
        private static final WsEncoder INSTANCE = new WsEncoder();
    }

    public static WsEncoder getInstance(){
        return WebsocketEncodeHolder.INSTANCE;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageLiteOrBuilder msg, List<Object> list) throws Exception {
        ByteBuf result = null;
        if (msg instanceof MessageLite) {
            // 没有build的Protobuf消息
            result = wrappedBuffer(((MessageLite) msg).toByteArray());
        }
        if (msg instanceof MessageLite.Builder) {
            // 经过build的Protobuf消息
            result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
        }

        // 将Protbuf消息包装成Binary Frame 消息
        WebSocketFrame frame = new BinaryWebSocketFrame(result);
        list.add(frame);
    }
}
