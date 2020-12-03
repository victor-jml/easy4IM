package pers.enoch.im.server.session;


import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zy
 * @Date: 2020/12/3 23:35
 * @Description: 用来管理在线用户的 Channel
 */
public class Session {
    private static final Map<Long, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(64);

    public void put(Long id ,Channel channel) {
        CHANNEL_MAP.put(id,channel);
    }

    public Channel get(Long id){
        return CHANNEL_MAP.get(id);
    }

    public static Map<Long,Channel> getChannelMa(){
        return CHANNEL_MAP;
    }

    public static void remove(Channel channel){
        CHANNEL_MAP.entrySet().stream().filter(entry ->
            entry.getValue() == channel
        ).forEach(entry ->{
            CHANNEL_MAP.remove(entry.getKey());
        });
    }
}
