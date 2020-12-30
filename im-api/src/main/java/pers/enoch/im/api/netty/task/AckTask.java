package pers.enoch.im.api.netty.task;


import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.common.protobuf.Ack;

/**
 * @Author yang.zhao
 * Date: 2020/12/30
 * Description:
 **/
@Slf4j
public class AckTask implements Runnable{


    private Ack.AckMsg ackMsg;

    private Channel channel;

    public AckTask(Channel channel,Ack.AckMsg ackMsg){
        this.ackMsg = ackMsg;
        this.channel = channel;

    }

    @Override
    public void run() {
        // todo update offlineMsg type
        log.info("receive ack from client : {} " , ackMsg.getSender());
    }
}
