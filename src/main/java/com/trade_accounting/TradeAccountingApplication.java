package com.trade_accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TradeAccountingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TradeAccountingApplication.class, args);
    }

}
