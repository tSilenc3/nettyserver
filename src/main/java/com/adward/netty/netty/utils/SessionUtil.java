package com.adward.netty.netty.utils;

import com.adward.netty.entity.User;
import com.adward.netty.netty.net.IoSession;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class SessionUtil {
    private static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");

    private static AttributeKey<User> USER_KEY = AttributeKey.valueOf("user");

    public static boolean addChannelSession(Channel channel, IoSession ioSession) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.compareAndSet(null, ioSession);
    }

    public static IoSession getSessionByChannel(Channel channel) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.get();
    }

    public static boolean addChannelUser(Channel channel, User user) {
        Attribute<User> userAttr = channel.attr(USER_KEY);
        return userAttr.compareAndSet(null, user);
    }

    public static User getUserByChannel(Channel channel) {
        Attribute<User> userAttr = channel.attr(USER_KEY);
        return userAttr.get();
    }

    public static String getIp(Channel channel) {
        return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString();
    }
}
