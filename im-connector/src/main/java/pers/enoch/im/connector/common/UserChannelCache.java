package pers.enoch.im.connector.common;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yang.zhao
 * Date: 2021/1/22
 * Description: 用户和通道缓存
 **/
@Slf4j
public class UserChannelCache {

    /**
     *  用户id和channel映射map
     */
    private static Map<String, Channel> userChannelMap = new ConcurrentHashMap<String, Channel>(1024);
    private static Map<Channel, String> channelUserMap = new ConcurrentHashMap<Channel, String>(1024);

    public static void set(String userId, Channel channel) {
        if (userId == null || channel == null) {
            return;
        }
        userChannelMap.remove(userId);
        channelUserMap.remove(channel);
        userChannelMap.put(userId, channel);
        channelUserMap.put(channel, userId);
    }

    public static Channel getChannel(String userId) {
        return userChannelMap.get(userId);
    }

    public static String getUserId(Channel channel) {
        return channelUserMap.get(channel);
    }

    public static void removeChannel(Channel channel) {
        if (channel == null) {
            return;
        }
        String userId = getUserId(channel);
        log.warn("关闭用户通道:" + userId);
        if (userId != null) {
            userChannelMap.remove(userId);
        }
        channelUserMap.remove(channel);
    }

    public static boolean hasLogin(String userId){
        Channel channel = getChannel(userId);
        return channel != null;
    }
}
