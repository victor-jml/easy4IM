package pers.enoch.im.api.netty.task;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.protobuf.Logout;


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
        // remove User-Channel relation
        SessionUtil.unBindSession(request.getUid());
        // change User online status
        userStatusService.offline(request.getUid());
        // close channel
        channel.close();
    }
}
