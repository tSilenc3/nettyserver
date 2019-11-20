package com.adward.netty.netty.logic.user.req;

import com.adward.netty.base.SpringContext;
import com.adward.netty.netty.logic.user.UserService;
import com.adward.netty.netty.message.AbstractPacket;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.adward.netty.netty.utils.SessionUtil;
import com.alibaba.fastjson.JSONObject;

public class ReqUserRegister extends AbstractPacket {
    /**
     * param:
     * String userName
     * String password
     */

    @Override
    public PacketType getPacketType() {
        return PacketType.register;
    }

    @Override
    public void execPacket(IoSession ioSession) {
        UserService userService = SpringContext.getUserService();

        JSONObject data = getReqParam();
        String userName = data.getString("userName");
        String password = data.getString("password");
        userService.registerNewUser(ioSession.getChannel(), userName, password);

    }
}
