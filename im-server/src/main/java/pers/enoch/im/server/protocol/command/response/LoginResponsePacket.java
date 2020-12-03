package pers.enoch.im.server.protocol.command.response;

import lombok.Data;
import pers.enoch.im.server.protocol.Packet;
import pers.enoch.im.server.protocol.command.Command;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:47
 * @Description:
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    /**
     * 登录是否成功
     */
    private boolean success;

    /**
     * 登录失败原因
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
