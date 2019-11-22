package com.adward.netty.base;

import com.adward.netty.netty.net.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SessionManager {
    INSTANCE,
    ;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 用户和session的对应
     */
    private Map<Long, IoSession> userId2Session = new ConcurrentHashMap<>();


    public IoSession getSessionByUserId(Long userId) {
        return this.userId2Session.get(userId);
    }

    public void registerSession(Long userId, IoSession session) {
        userId2Session.put(userId, session);
    }

    /**
     * 注销session
     */
    public void unRegisterSession(Long userId) {
        this.userId2Session.remove(userId);
    }
}
