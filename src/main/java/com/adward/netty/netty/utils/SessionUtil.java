package com.adward.netty.netty.utils;

import com.adward.netty.netty.net.IoSession;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class SessionUtil {
    public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");

    public static boolean addChannelSession(Channel channel, IoSession ioSession) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.compareAndSet(null, ioSession);
    }

    public static IoSession getSessionByChannel(Channel channel) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.get();
    }

    public static String getIp(Channel channel) {
        return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString();
    }
}
