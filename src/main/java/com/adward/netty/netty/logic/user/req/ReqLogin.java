package com.adward.netty.netty.logic.user.req;

import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;

public class ReqLogin extends AbstractPacket {

    @Override
    public PacketType getPacketType() {
        return PacketType.login;
    }

    @Override
    public void execPacket(IoSession ioSession) {

    }
}
