package com.adward.netty.netty.logic.room.req;

import com.adward.netty.base.SpringContext;
import com.adward.netty.entity.User;
import com.adward.netty.entity.vo.RoomUserVO;
import com.adward.netty.netty.logic.Common.ResponseData;
import com.adward.netty.netty.logic.room.RoomContext;
import com.adward.netty.netty.logic.room.RoomService;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.adward.netty.netty.utils.SessionUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReqCreateRoom extends AbstractPacket {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PacketType getPacketType() {
        return PacketType.createRoom;
    }

    @Override
    public void execPacket(IoSession ioSession) {
        RoomService roomService = SpringContext.getRoomService();
        roomService.createRoom(ioSession);
    }
}
