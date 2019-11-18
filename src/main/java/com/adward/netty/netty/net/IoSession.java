package com.adward.netty.netty.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.message.AbstractPacket;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class IoSession {

    private AtomicInteger dispatchKeyGenerator = new AtomicInteger();

    private Channel channel;

    private String ipAddr;

    public IoSession() {

    }

    public IoSession(Channel channel) {
        this.channel = channel;
        this.ipAddr = ((InetSocketAddress)channel.remoteAddress()).getAddress().getHostAddress();
    }

    public void sendPacket(Object packet) {
        if (packet == null) {
            return;
        }

        if (channel != null) {
            String data = JSON.toJSONString(packet);
            channel.writeAndFlush(new TextWebSocketFrame(data));
        }
    }

    public void sendPacket(JSONObject packet) {
        if (packet == null) {
            return;
        }
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(packet.toJSONString()));
        }
    }
}
