package com.adward.netty.netty.logic.user.req;

import com.adward.netty.base.SpringContext;
import com.adward.netty.netty.logic.user.UserService;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.alibaba.fastjson.JSONObject;

public class ReqLogin extends AbstractPacket {
    /**
     * param:
     * String userName
     * String password
     */

    @Override
    public PacketType getPacketType() {
        return PacketType.login;
    }

    @Override
    public void execPacket(IoSession ioSession) {

        JSONObject data = getReqParam();

        String userName = data.getString("userName");
        String password = data.getString("password");

        UserService userService = SpringContext.getUserService();
        userService.login(ioSession.getChannel(), userName, password );
    }
}
