package com.adward.netty.netty.logic.room.req;

import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;

public class ReqStartGame extends AbstractPacket {

    @Override
    public PacketType getPacketType() {
        return PacketType.startGame;
    }

    @Override
    public void execPacket(IoSession ioSession) {

    }
}
