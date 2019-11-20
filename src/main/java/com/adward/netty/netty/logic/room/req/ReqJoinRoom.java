package com.adward.netty.netty.logic.room.req;

import com.adward.netty.base.SpringContext;
import com.adward.netty.netty.logic.room.RoomService;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.alibaba.fastjson.JSONObject;

public class ReqJoinRoom extends AbstractPacket {

    /**
     *param:
     * String roomId
     */

    @Override
    public PacketType getPacketType() {
        return null;
    }

    @Override
    public void execPacket(IoSession ioSession) {
        String roomId = getReqParam().getString("roomId");

        RoomService roomService = SpringContext.getRoomService();
        roomService.joinRoom(ioSession, roomId);
    }
}
