package com.adward.netty.base;

import com.adward.netty.netty.logic.room.RoomService;
import com.adward.netty.netty.logic.user.UserService;
import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static SpringContext self;

    @PostConstruct
    public void init() {
        self = this;
    }

    private static UserService userService;

    private static RoomService roomService;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static UserService getUserService() {
        return userService;
    }
    @Resource
    public void setUserService(UserService userService) {
        SpringContext.userService = userService;
    }

    public static RoomService getRoomService() {
        return roomService;
    }
    @Resource
    public void setRoomService(RoomService roomService) {
        SpringContext.roomService = roomService;
    }
}
