package com.adward.netty.netty.logic.room;

import com.adward.netty.entity.User;
import com.adward.netty.entity.vo.RoomUserVO;
import com.adward.netty.netty.logic.Common.ResponseData;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import com.adward.netty.netty.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void createRoom(IoSession session) {
        User user = SessionUtil.getUserByChannel(session.getChannel());
        if (user == null) {
            ResponseData responseData = new ResponseData(PacketType.createRoom.getType(), "用户未登录");
            session.sendPacket(responseData);
            return;
        }
        RoomUserVO userVO = new RoomUserVO();
        userVO.setUserId(user.getId());
        userVO.setUserName(user.getUserName());

        RoomContext roomContext = new RoomContext(userVO, session);
        logger.info(roomContext.toString());
    }

    public void joinRoom(IoSession session, String roomId) {
        if (!RoomContext.roomMap.containsKey(roomId)) {
            ResponseData responseData = new ResponseData(PacketType.joinRoom.getType(), "房间不存在");
            session.sendPacket(responseData);
        } else {
            User user = SessionUtil.getUserByChannel(session.getChannel());

            RoomContext room = RoomContext.roomMap.get(roomId);

            RoomUserVO userVO = new RoomUserVO();
            userVO.setUserName(user.getUserName());
            userVO.setUserId(user.getId());

            room.joinRoom(userVO, session);
        }
    }

    public void quitRoom(IoSession session, String roomId) {
        if (!RoomContext.roomMap.containsKey(roomId)) {
            ResponseData responseData = new ResponseData(PacketType.quitRoom.getType(), "房间不存在");
            session.sendPacket(responseData);
        } else {
            RoomContext roomContext = RoomContext.roomMap.get(roomId);
            roomContext.outRoom(session, SessionUtil.getUserByChannel(session.getChannel()).getId());
        }
    }
}
