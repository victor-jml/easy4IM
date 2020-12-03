package pers.enoch.im.server.util;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import pers.enoch.im.server.attribute.Attributes;
import pers.enoch.im.server.session.Session;

import java.util.Map;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:51
 * @Description: 用于保存 用户 -- Channel
 * 保存 群聊id -- 群聊用户id
 */
@Slf4j
public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = Maps.newConcurrentMap();

    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = Maps.newConcurrentMap();

    /**
     * 登录
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 注销
     * @param channel
     */
    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            log.info(session + "退出登录");
        }
    }

    /**
     * 判断是否已经登录
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        return getSession(channel) != null;
    }

    /**
     * 获取当前channel绑定的session
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
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
