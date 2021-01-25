package pers.enoch.im.connector.service.impl;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pers.enoch.im.common.protobuf.Msg;
import pers.enoch.im.connector.service.SendMsgService;


/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Service
@Slf4j
public class GroupSendMsgServiceImpl implements SendMsgService {

    @Override
    public void sendMsg(Channel channel, Msg.SendMsg sendMsg) {

    }
}
