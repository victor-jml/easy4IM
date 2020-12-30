package pers.enoch.im.api.service.impl;

import io.netty.channel.Channel;
import pers.enoch.im.api.service.MsgService;
import pers.enoch.im.common.protobuf.Msg;



/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
public class GroupMsgServiceImpl implements MsgService {

    @Override
    public void sendMsg(Channel channel, Msg.SendMsg sendMsg) {

    }
}
