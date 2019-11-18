package com.adward.netty.base;

import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static SpringContext self;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        self = this;
    }
}
