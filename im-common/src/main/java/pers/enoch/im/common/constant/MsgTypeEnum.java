package pers.enoch.im.common.constant;

import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.KeepAlive;
import pers.enoch.im.common.protobuf.Single;

import java.util.stream.Stream;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public enum MsgTypeEnum {


    /**
     * 验证请求
     */
    AUTH_REQ(1, Auth.AuthRequest.class),

    /**
     * 验证回执
     */
    AUTH_RES(2,Auth.AuthResponse.class),

    /**
     * 私聊发生请求
     */
    SINGLE_SEND_REQ(3,Single.SingleSendRequest.class),

    /**
     * 私聊发送回执
     */
    SINGLE_SEND_RES(4,Single.SingleSendResponse.class),

    /**
     * 私聊推送请求
     */
    SINGLE_PUSH_REQ(5,Single.SinglePushRequest.class),

    /**
     * 私聊推送回执
     */
    SINGLE_PUSH_RES(6,Single.SinglePushResponse.class),

    /**
     * 心跳检测
     */
    HEART_REQ(7, KeepAlive.KeepAliveReq.class),

    /**
     * 心跳检测ACK
     */
    HEART_RES(8, KeepAlive.KeepAliveRes.class);




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
