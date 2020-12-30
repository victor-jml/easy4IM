package pers.enoch.im.server.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author yang.zhao
 * @Date 2020/12/3 10:21
 * @Version 1.0
 * @Description websocket解码
 **/
@Slf4j
@ChannelHandler.Sharable
public class WsDecoder extends MessageToMessageDecoder<WebSocketFrame> {

    private static class WebSocketDecoderHolder{
        private static final WsDecoder INSTANCE = new WsDecoder();
    }

    public static WsDecoder getInstance(){
        return WebSocketDecoderHolder.INSTANCE;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame, List<Object> list) throws Exception {
        log.info("received client msg ------------------------");
        if (frame instanceof TextWebSocketFrame) {
            // 文本消息
            TextWebSocketFrame textFrame = (TextWebSocketFrame)frame;
            log.info("MsgType is TextWebSocketFrame");
        } else if (frame instanceof BinaryWebSocketFrame) {
            // 二进制消息
            ByteBuf buf = ((BinaryWebSocketFrame) frame).content();
            list.add(buf);
            // 自旋累加
            buf.retain();
            log.info("MsgType is BinaryWebSocketFrame");
        } else if (frame instanceof PongWebSocketFrame) {
            // PING存活检测消息
            log.info("MsgType is PongWebSocketFrame ");
        } else if (frame instanceof CloseWebSocketFrame) {
            // 关闭指令消息
            log.info("MsgType is CloseWebSocketFrame");
            channelHandlerContext.close();
        }
    }

}
