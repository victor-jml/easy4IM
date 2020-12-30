package pers.enoch.im.common.constant;

import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;

import java.util.stream.Stream;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public enum MsgTypeEnum {


    /**
     * 登录,心跳请求包
     */
    STATUS_REQ(1, Status.Request.class),

    /**
     * 登录,心跳回执包
     */
    STATUS_RES(2,Status.Response.class),

    /**
     * 消息ACK包
     */
    ACK(3, Ack.AckMsg.class),

    /**
     * 消息
     */
    CHAT(4, Msg.SendMsg.class);



    int code;
    Class<?> clazz;

    MsgTypeEnum(int code, Class<?> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public static MsgTypeEnum getByCode(int code) {
        return Stream.of(values()).filter(t -> t.code == code)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static MsgTypeEnum getByClass(Class<?> clazz) {
        return Stream.of(values()).filter(t -> t.clazz == clazz)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int getCode() {
        return code;
    }
}
