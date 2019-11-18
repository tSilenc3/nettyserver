package com.adward.netty.netty.logic.user.res;

import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;

public class ResUserRegister extends AbstractPacket {

    private int resultCode;


    @Override
    public PacketType getPacketType() {
        return null;
    }

    @Override
    public void execPacket(IoSession ioSession) {

    }

}
