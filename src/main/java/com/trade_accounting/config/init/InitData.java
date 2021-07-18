package com.trade_accounting.config.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public abstract class InitData {

    @PostConstruct
    abstract void init();

     static int randomInt(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}