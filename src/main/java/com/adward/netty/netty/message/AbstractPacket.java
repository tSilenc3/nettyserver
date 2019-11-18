package com.adward.netty.netty.message;

import com.alibaba.fastjson.JSONObject;
import com.adward.netty.netty.net.IoSession;

public abstract class AbstractPacket {

    abstract public PacketType getPacketType();

    abstract public void execPacket(IoSession ioSession);

    private JSONObject reqParam;

    public JSONObject getReqParam() {
        return reqParam;
    }

    public void setReqParam(JSONObject reqParam) {
        this.reqParam = reqParam;
    }
}
