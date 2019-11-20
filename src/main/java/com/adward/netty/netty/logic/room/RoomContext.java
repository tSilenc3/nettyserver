package com.adward.netty.netty.logic.room;

import com.adward.netty.entity.vo.RoomUserVO;
import com.adward.netty.netty.logic.Common.ResponseData;
import com.adward.netty.netty.message.PacketType;
import com.adward.netty.netty.net.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RoomContext {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static ConcurrentHashMap<String, RoomContext> roomMap = new ConcurrentHashMap<>();

    private int MAX_PLAYER_COUNT = 3;

    private Map<Long, RoomUserVO> users;

    private String roomId;

    public RoomContext(RoomUserVO owner, IoSession session) {
        users = new ConcurrentHashMap<>();
        roomId = getRoomId();
        roomMap.put(roomId, this);
        joinRoom(owner, session);
    }

    private String getRoomId() {
        String id = UUID.randomUUID().toString();
        while (roomMap.get(id) != null) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public void joinRoom(RoomUserVO userVO, IoSession session) {
        if (!users.containsKey(userVO.getUserId())) {
            userVO.setRoomId(roomId);
            users.put(userVO.getUserId(), userVO);
            ResponseData responseData = new ResponseData(PacketType.joinRoom.getType(), userVO);
            session.sendPacket(responseData);
            logger.info("玩家 {} 进入了房间", userVO.getUserName());
        } else {
            session.sendPacket(new ResponseData(PacketType.joinRoom.getType(), "玩家已经在房间"));
            logger.info("玩家已经在房间");
        }
    }

    public void outRoom(RoomUserVO userVO) {
        users.remove(userVO.getUserId());
        userVO.setRoomId(null);

        logger.info("用户 {} 退出了房间", userVO.getUserName());
        if (users.isEmpty()) {
            destroyRoom();
        }

    }

    public void destroyRoom() {
        roomMap.remove(roomId);
    }
}
