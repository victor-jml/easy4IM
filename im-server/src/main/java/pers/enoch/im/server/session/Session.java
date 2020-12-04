package pers.enoch.im.server.session;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 17:01
 * @Version 1.0
 * @Description 保存 （ 用户 - channel ）
 **/
public class Session {
    private static final Map<Long, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(64);

    public static void put(Long uid, Channel channel) {
        CHANNEL_MAP.put(uid, channel);
    }

    public static Channel get(Long uid) {
        return CHANNEL_MAP.get(uid);
    }

    public static Map<Long, Channel> getMAP() {
        return CHANNEL_MAP;
    }

    public static void remove(Channel channel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == channel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }
}
