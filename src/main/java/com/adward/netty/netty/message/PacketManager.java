package com.adward.netty.netty.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.net.IoSession;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum  PacketManager {
        INSTANCE
    ;
    public AbstractPacket createNewPacket(int port, JSONObject data) {
        Class<? extends AbstractPacket> packetClass = PacketType.getPacketClassByPort(port);

        if (packetClass == null) {
            throw new IllegalStateException("类型为" + port + "的包不存在");
        }

        AbstractPacket packet;

        try {
            packet = packetClass.newInstance();
            packet.setReqParam(data);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException("类型为" + port + "的包实例化失败");
        }

        return packet;
    }

    public void execPacket(IoSession session, AbstractPacket packet) {
        if (packet == null) return;

        try {
            Method method = packet.getClass().getMethod("execPacket", IoSession.class);
            method.invoke(packet, session);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
