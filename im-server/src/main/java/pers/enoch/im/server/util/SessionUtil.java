package pers.enoch.im.server.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:51
 * @Description: 用于保存 用户 -- Channel
 * 保存 群聊id -- 群聊用户id
 */
@Slf4j
public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<String ,Channel>(64);

    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<String ,ChannelGroup>(64);

    /**
     * 登录
     * @param userId
     * @param channel
     */
    public static void bindSession(String userId , Channel channel){
        userIdChannelMap.put(userId,channel);
    }

    /**
     * 注销
     * @param userId
     */
    public static void unBindSession(String userId){
        if(hasLogin(userId)){
            userIdChannelMap.remove(userId);
        }
    }

    /**
     * 判断是否已经登录
     * @param userId
     * @return
     */
    public static boolean hasLogin(String userId){
        return userIdChannelMap.containsKey(userId);
    }

    /**
     * 获取channel
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }

    /**
     * 创建群聊
     * @param groupId
     * @param channelGroup
     */
    public static void bindChannelGroup(String groupId,ChannelGroup channelGroup){
        groupIdChannelGroupMap.put(groupId,channelGroup);
    }

    /**
     * 通过群聊id获取channelGroup
     * @param groupId
     * @return
     */
    public static ChannelGroup getChannelGroup(String groupId){
        return groupIdChannelGroupMap.get(groupId);
    }
}
