package pers.enoch.im.api.service.impl;

import io.netty.channel.Channel;
import pers.enoch.im.api.service.SendMsgService;
import pers.enoch.im.common.protobuf.Msg;



/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
public class GroupSendMsgServiceImpl implements SendMsgService {

    @Override
    public void sendMsg(Channel channel, Msg.SendMsg sendMsg) {

    }
}
