package com.adward.netty.netty.net;

import com.adward.netty.netty.utils.SessionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.message.AbstractPacket;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.CloseReason;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class IoSession {
    private Logger logger = LoggerFactory.getLogger(getClass());

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

    public void close(SessionCloseReason reason) {
        try {
            if (this.channel == null) {
                return;
            }

            if (channel.isOpen()) {
                channel.close();
                logger.info("close session[{}], reason is {}", SessionUtil.getUserByChannel(this.channel).getId(), reason);
            } else  {
                logger.info("session[{}] already close, reason is {}", SessionUtil.getUserByChannel(this.channel).getId(), reason);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
