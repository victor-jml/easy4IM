package pers.enoch.im.client.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import pers.enoch.im.common.constant.MsgTypeEnum;
import pers.enoch.im.common.exception.IMException;
import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.common.protobuf.Status;

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

        parseFunctionMap.put(MsgTypeEnum.STATUS_REQ, Status.Request::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.STATUS_RES, Status.Response::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.CHAT, Msg.SendMsg::parseFrom);
        parseFunctionMap.put(MsgTypeEnum.ACK, Ack.AckMsg::parseFrom);
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
