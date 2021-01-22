package pers.enoch.im.connector.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.common.model.SendMsg;
import pers.enoch.im.common.protobuf.Ack;
import pers.enoch.im.connector.mapper.SendMsgMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description:
 **/
@Slf4j
@Service
public class OfflineService {
    @Resource
    private SendMsgMapper sendMsgMapper;


    /**
     * get user All-Offline msg
     * @param userId
     */
    public List<SendMsg> pollOfflineMsg(String userId){
        QueryWrapper<SendMsg> queryOfflineMsg = new QueryWrapper<>();
        List<SendMsg> result = null;
        Map<String,Object> queryMap = Maps.newHashMap();
        queryMap.put("receiver_id",userId);
        queryMap.put("delivered",0);
        queryOfflineMsg.allEq(queryMap);
        try {
            result = sendMsgMapper.selectList(queryOfflineMsg);
        } catch (Exception e) {
            log.error(" 查询用户id为 {}  的离线消息错误",userId);
            return null;
        }
        return result;
    }


    public void ackMsg(Channel channel, Ack.AckMsg ackMsg){
        UpdateWrapper<SendMsg> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("msg_id",ackMsg.getAckMsgId()).set("delivered",1);
        try {
            sendMsgMapper.update(null,updateWrapper);
        } catch (Exception e) {
            log.error("update delivered msg: {} failed",ackMsg.getAckMsgId());
        }
    }
}
