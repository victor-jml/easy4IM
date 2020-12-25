package pers.enoch.im.server.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import pers.enoch.im.common.constant.MsgTypeEnum;
import pers.enoch.im.common.exception.IMException;
import pers.enoch.im.common.protobuf.Auth;
import pers.enoch.im.common.protobuf.Single;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yang.zhao
 * Date: 2020/12/25
 * Description:
 **/
public class ParseService {
    private Map<MsgTypeEnum, Parse> parseFunctionMap;

    public ParseService() {
        parseFunctionMap = new HashMap<>(MsgTypeEnum.values().length);

        parseFunctionMap.put(MsgTypeEnum.AUTH_REQ, Auth.AuthRequest::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.AUTH_RES, Auth.AuthResponse::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.SINGLE_SEND_REQ, Single.SingleSendRequest::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.SINGLE_SEND_RES, Single.SinglePushResponse::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.SINGLE_PUSH_REQ, Single.SinglePushRequest::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.SINGLE_PUSH_RES,Single.SinglePushResponse::parseFrom);
    }

    public Message getMsgByCode(int code, byte[] bytes) throws InvalidProtocolBufferException {
        MsgTypeEnum msgType = MsgTypeEnum.getByCode(code);
        Parse parseFunction = parseFunctionMap.get(msgType);
        if (parseFunction == null) {
            throw new IMException("[msg parse], no proper parse function, msgType: " + msgType.name());
        }
        return parseFunction.process(bytes);
    }

    @FunctionalInterface
    public interface Parse {
        /**
         * parse msg
         *
         * @param bytes msg bytes
         * @return
         * @throws InvalidProtocolBufferException
         */
        Message process(byte[] bytes) throws InvalidProtocolBufferException;
    }
}
