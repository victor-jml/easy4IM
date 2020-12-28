package pers.enoch.im.api.netty.service;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;
import pers.enoch.im.api.netty.util.IDUtil;
import pers.enoch.im.api.netty.util.SessionUtil;
import pers.enoch.im.api.service.UserStatusService;
import pers.enoch.im.common.protobuf.Single;

import javax.annotation.Resource;

/**
 * @Author yang.zhao
 * Date: 2020/12/28
 * Description: demo: send message client to client
 **/
@Component
public class SingleChatService {
    @Resource
    private UserStatusService userStatusService;


    public void sendMessage(Channel send, Channel receive, Single.SingleSendRequest sendRequest){
        // get UserId
        String receiveId = SessionUtil.getUserId(receive);
        // check User isOnline
        boolean isOnline = userStatusService.checkLogin(receiveId);
        Single.SingleSendResponse response = Single.SingleSendResponse.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .build();
        if(isOnline){
            receive.writeAndFlush(sendRequest);
        }
        send.writeAndFlush(response);
    }
}
