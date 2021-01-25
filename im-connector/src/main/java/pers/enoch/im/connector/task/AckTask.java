package pers.enoch.im.connector.task;


import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.connector.SpringBeanUtil;
import pers.enoch.im.connector.service.OfflineService;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Slf4j
public class AckTask implements Runnable{
    private final OfflineService offlineService;

    private Ack.AckMsg ackMsg;

    private Channel channel;

    public AckTask(Channel channel,Ack.AckMsg ackMsg){
        this.ackMsg = ackMsg;
        this.channel = channel;
        offlineService = SpringBeanUtil.getBean(OfflineService.class);

    }

    @Override
    public void run() {
        // todo update offlineMsg receiveType
        offlineService.ackMsg(channel,ackMsg);
    }
}
