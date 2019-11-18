package com.adward.netty.netty.logic;

import com.adward.netty.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;

public class ReqHeart extends AbstractPacket {

    @Override
    public PacketType getPacketType() {
        return PacketType.heart;
    }

    @Override
    public Object getData() {
        return super.getData();
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
    }

    @Override
    public void execPacket(IoSession ioSession) {
        ioSession.sendPacket(new User("aa", "123456"));
    }
}
