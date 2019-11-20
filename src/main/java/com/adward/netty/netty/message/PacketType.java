package com.adward.netty.netty.message;

import com.adward.netty.netty.logic.ReqHeart;
import com.adward.netty.netty.logic.room.req.ReqCreateRoom;
import com.adward.netty.netty.logic.room.req.ReqJoinRoom;
import com.adward.netty.netty.logic.user.req.ReqLogin;
import com.adward.netty.netty.logic.user.req.ReqUserRegister;

import java.util.HashMap;
import java.util.Map;

public enum PacketType {
    /**
     *心跳
     */
    heart(106, ReqHeart.class),

    /**
     * 用户相关
     */
    login(20013, ReqLogin.class),
    register(20015, ReqUserRegister.class),
    /**
     * 房间相关
     */
    createRoom(30001, ReqCreateRoom.class),
    joinRoom(30002, ReqJoinRoom.class)
    ;
    private int type;
    private Class<? extends AbstractPacket> packetClass;
    private static Map<Integer, Class<? extends AbstractPacket>> packetClassMap = new HashMap<>();

    PacketType(int type, Class<? extends AbstractPacket> packetClass) {
        this.type = type;
        this.packetClass = packetClass;
    }

    public static void initPacket() {
        for (PacketType value : PacketType.values()) {
            int type = value.getType();
            if (packetClassMap.get(type) != null) {
                throw new IllegalStateException("packet 定义重复");
            }
            packetClassMap.put(type, value.getPacketClass());
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Class<? extends AbstractPacket> getPacketClass() {
        return packetClass;
    }

    public void setPacketClass(Class<? extends AbstractPacket> packetClass) {
        this.packetClass = packetClass;
    }

    public static Class<? extends AbstractPacket> getPacketClassByPort(int port) {
        return packetClassMap.get(port);
    }

}
