package pers.enoch.im.server.task;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.generate.Logout;

/**
 * @Author yang.zhao
 * @Date 2020/12/15 17:30
 * @Version 1.0
 * @Description
 **/
public class LogoutMsgTask implements Runnable {

    @Autowired
    private UserStatusService userStatusService;

    private Channel channel;
    private Logout.LogoutRequest request;

    public LogoutMsgTask(Channel channel, Logout.LogoutRequest request){
        this.request = request;
        this.channel = channel;
    }

    @Override
    public void run() {

    }
}
