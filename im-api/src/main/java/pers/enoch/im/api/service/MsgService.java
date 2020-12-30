package pers.enoch.im.api.service;

import io.netty.channel.Channel;
import pers.enoch.im.common.protobuf.Msg;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
public interface MsgService {
    /**
     * define sendMsg interface , two implement class (SingleChat & GroupChat)
     * @param channel
     * @param sendMsg
     */
    void sendMsg(Channel channel, Msg.SendMsg sendMsg);
}
