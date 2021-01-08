package pers.enoch.im.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.enoch.im.api.mapper.OfflineMsgMapper;
import pers.enoch.im.api.model.OfflineMsg;

import javax.annotation.Resource;
import java.util.List;

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
     * @return
     */
    public boolean saveOfflineMsg(OfflineMsg offlineMsg){
        try {
             offlineMsgMapper.insert(offlineMsg);
        } catch (Exception e) {
            log.error("insert offline message error");
            return false;
        }
        return true;
    }


    /**
     * get user All-Offline msg
     * @param userId
     */
    public List<OfflineMsg> pollOfflineMsg(String userId){
        QueryWrapper<OfflineMsg> queryOfflineMsg = new QueryWrapper<>();
        List<OfflineMsg> result = null;
        queryOfflineMsg.eq("userId",userId);
        try {
            result = offlineMsgMapper.selectList(queryOfflineMsg);
        } catch (Exception e) {
            log.error(" 查询用户id为 {}  的离线消息错误",userId);
            return null;
        }
        return result;
    }
}
