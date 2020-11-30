package pers.enoch.im.protocol.command.request;

import lombok.Data;
import pers.enoch.im.protocol.Packet;
import pers.enoch.im.protocol.command.Command;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:46
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
