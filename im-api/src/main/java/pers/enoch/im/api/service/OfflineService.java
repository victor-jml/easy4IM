package pers.enoch.im.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.OfflineMsgMapper;
import pers.enoch.im.api.model.OfflineMsg;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author yang.zhao
 * Date: 2021/1/7
 * Description:
 **/
@Slf4j
@Service
public class OfflineService {
    @Resource
    private OfflineMsgMapper offlineMsgMapper;

    /**
     * save Offline msg
     * @return Long insert offline-msg_id
     */
    public Long saveOfflineMsg(OfflineMsg offlineMsg){
        try {
             offlineMsgMapper.insert(offlineMsg);
        } catch (Exception e) {
            log.error("insert offline message error");
            return null;
        }
        return offlineMsg.getMsgId();
    }


    /**
     * get user All-Offline msg
     * @param userId
     */
    public List<OfflineMsg> pollOfflineMsg(String userId){
        QueryWrapper<OfflineMsg> queryOfflineMsg = new QueryWrapper<>();
        List<OfflineMsg> result = null;
        Map<String,Object> queryMap = Maps.newHashMap();
        queryMap.put("msg_to",userId);
        queryMap.put("delivered",0);
        queryOfflineMsg.allEq(queryMap);
        try {
            result = offlineMsgMapper.selectList(queryOfflineMsg);
        } catch (Exception e) {
            log.error(" 查询用户id为 {}  的离线消息错误",userId);
            return null;
        }
        return result;
    }

    /**
     * change offline-message type
     * @param msgId
     * @return
     */
    public boolean ackOfflineMsg(Long msgId){
        OfflineMsg offlineMsg = OfflineMsg.builder()
                .msgId(msgId)
                .delivered(1)
                .build();
        try {
            offlineMsgMapper.updateById(offlineMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
