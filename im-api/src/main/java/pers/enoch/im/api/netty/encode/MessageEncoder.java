package pers.enoch.im.api.netty.encode;

import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.constant.MsgTypeEnum;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
@Slf4j
public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        try {
            byte[] bytes = msg.toByteArray();
            int code = MsgTypeEnum.getByClass(msg.getClass()).getCode();
            int length = bytes.length;

            ByteBuf buf = Unpooled.buffer(8 + length);
            buf.writeInt(length);
            buf.writeInt(code);
            buf.writeBytes(bytes);
            out.writeBytes(buf);

            log.debug("send message, remoteAddress: {}, content length {}, msgTypeCode: {}", ctx.channel().remoteAddress(), length, code);
        } catch (Exception e) {
            log.error("[client] msg encode has error", e);
        }
    }
}

