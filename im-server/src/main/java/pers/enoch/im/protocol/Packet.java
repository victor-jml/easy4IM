package pers.enoch.im.protocol;

import lombok.Data;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:41
 * @Description:
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 获取请求的指令
     * @return Byte
     */
    public abstract Byte getCommand();
}
