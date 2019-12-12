package com.adward.netty.netty.message;

import com.adward.netty.netty.logic.ReqHeart;
import com.adward.netty.netty.logic.battle.req.ReqBattleReady;
import com.adward.netty.netty.logic.battle.req.ReqMove;
import com.adward.netty.netty.logic.room.req.ReqCreateRoom;
import com.adward.netty.netty.logic.room.req.ReqJoinRoom;
import com.adward.netty.netty.logic.room.req.ReqQuitRoom;
import com.adward.netty.netty.logic.room.req.ReqStartGame;
import com.adward.netty.netty.logic.user.req.ReqLogin;
import com.adward.netty.netty.logic.user.req.ReqLoginInfo;
import com.adward.netty.netty.logic.user.req.ReqUserRegister;

import java.util.HashMap;
import java.util.Map;

public enum PacketType {
    /**
     *心跳
     */
    heart("heart", ReqHeart.class),

    /**
     * 用户相关
     */
    login("login", ReqLogin.class),
    loginInfo("loginInfo", ReqLoginInfo.class),
    register("register", ReqUserRegister.class),
    /**
     * 房间相关
     */
    createRoom("30001", ReqCreateRoom.class),
    joinRoom("30002", ReqJoinRoom.class),
    quitRoom("30003",ReqQuitRoom.class),
    startGame("30004", ReqStartGame.class),
    /**
     * 战斗相关
     */
    ready("40001", ReqBattleReady.class),
    move("40002", ReqMove.class)

    ;
    private String type;
    private Class<? extends AbstractPacket> packetClass;
    private static Map<String, Class<? extends AbstractPacket>> packetClassMap = new HashMap<>();

    PacketType(String type, Class<? extends AbstractPacket> packetClass) {
        this.type = type;
        this.packetClass = packetClass;
    }

    public static void initPacket() {
        for (PacketType value : PacketType.values()) {
            String type = value.getType();
            if (packetClassMap.get(type) != null) {
                throw new IllegalStateException("packet 定义重复");
            }
            packetClassMap.put(type, value.getPacketClass());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<? extends AbstractPacket> getPacketClass() {
        return packetClass;
    }

    public void setPacketClass(Class<? extends AbstractPacket> packetClass) {
        this.packetClass = packetClass;
    }

    public static Class<? extends AbstractPacket> getPacketClassByPort(String port) {
        return packetClassMap.get(port);
    }

}
