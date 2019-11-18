package com.adward.netty.netty.message;

import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.net.IoSession;

public abstract class AbstractPacket {
    public Object data;

    abstract public PacketType getPacketType();

    abstract public void execPacket(IoSession ioSession);

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
