package pers.enoch.im.server.session;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yang.zhao
 * @Date 2020/12/4 17:01
 * @Version 1.0
 * @Description 保存用户连接信息
 **/
public class Session {
    private static final Map<String , ChannelHandlerContext> CHANNEL_MAP = new ConcurrentHashMap<>(64);

    public static void put(String uid, ChannelHandlerContext ctx) {
        CHANNEL_MAP.put(uid, ctx);
    }

    public static boolean containsUser(String uid){
        return CHANNEL_MAP.containsKey(uid);
    }

    public static ChannelHandlerContext get(Long uid) {
        return CHANNEL_MAP.get(uid);
    }

    public static Map<String , ChannelHandlerContext> getMap() {
        return CHANNEL_MAP;
    }

    public static void remove(ChannelHandlerContext ctx) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == ctx).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }
    public static void remove(String uid) {
        CHANNEL_MAP.remove(uid);
    }
}
