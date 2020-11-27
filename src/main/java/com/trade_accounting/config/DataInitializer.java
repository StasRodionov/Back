package com.trade_accounting.config;

import com.trade_accounting.models.TypeOfPrice;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @PostConstruct
    public void init() {
        initTypeOfPrices();
    }

    private void initTypeOfPrices() {
        new TypeOfPrice("Оптовая цена","1");
        new TypeOfPrice("Розничная цена", "2");
    }
}
