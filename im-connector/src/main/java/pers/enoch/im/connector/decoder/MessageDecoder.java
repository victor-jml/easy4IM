package pers.enoch.im.connector.decoder;

import cn.hutool.socket.protocol.MsgDecoder;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.enoch.im.connector.handle.ParseHandle;

import java.util.List;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public class MessageDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(MsgDecoder.class);

    private ParseHandle parseHandle;

    public MessageDecoder() {
        this.parseHandle = new ParseHandle();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        in.markReaderIndex();

        if (in.readableBytes() < 4) {
            in.resetReaderIndex();
            return;
        }

        int length = in.readInt();

        if (length < 0) {
            ctx.close();
            logger.error("[IM msg decoder]message length less than 0, channel closed");
            return;
        }

        if (length > in.readableBytes() - 4) {
            in.resetReaderIndex();
            return;
        }

        int code = in.readInt();
        ByteBuf byteBuf = Unpooled.buffer(length);

        in.readBytes(byteBuf);

        byte[] body = byteBuf.array();

        Message msg = parseHandle.getMsgByCode(code, body);
        out.add(msg);

        logger.debug("[IM msg decoder]received message: content length {}, msgTypeCode: {}", length, code);
    }
}
