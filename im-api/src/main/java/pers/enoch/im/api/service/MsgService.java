package pers.enoch.im.api.service;

import io.netty.channel.Channel;
import pers.enoch.im.common.protobuf.Single;

/**
 * @Author yang.zhao
 * Date: 2020/12/24
 * Description:
 **/
public interface MsgService {

    /**
     * send Message to User (Single Chat)
     * @param send
     * @param receive
     * @param SingleSendRequest
     */
    void sendMsgSingle(Channel send , Channel receive, Single.SingleSendRequest SingleSendRequest);
}
