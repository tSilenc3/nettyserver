package com.adward.netty.netty.logic.room.req;

import com.adward.netty.base.SpringContext;
import com.adward.netty.netty.logic.room.RoomService;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;

public class ReqQuitRoom extends AbstractPacket {
    /**
     *param:
     * long roomId
     */

    @Override
    public PacketType getPacketType() {
        return PacketType.quitRoom;
    }

    @Override
    public void execPacket(IoSession ioSession) {
        String userId = getReqParam().getString("roomId");

        RoomService roomService = SpringContext.getRoomService();
        roomService.quitRoom(ioSession, userId);
    }
}
