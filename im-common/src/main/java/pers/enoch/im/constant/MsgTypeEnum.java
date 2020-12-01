package pers.enoch.im.constant;

import java.util.stream.Stream;

/**
 * 消息类型
 *
 * @author: yang.zhao
 */
public enum MsgTypeEnum {
    /**
     * 聊天消息
     */

    /**
     * 回执消息
     */

    ;
    int code;
    Class<?> clazz;
    MsgTypeEnum(int code,Class<?> clazz){
        this.code = code;
        this.clazz = clazz;
    }

    public MsgTypeEnum getByCode(int code){
        return Stream.of(values()).filter(t -> t.code == code)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public MsgTypeEnum getByClass(Class<?> clazz){
        return Stream.of(values()).filter(t -> t.clazz == clazz)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public int getCode(){
        return code;
    }
}
