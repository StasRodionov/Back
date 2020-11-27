package com.trade_accounting.config;

import com.trade_accounting.models.TypeOfPrice;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @PostConstruct
    public void init() {
        initTypeOfPrices("Оптовая цена");
        initTypeOfPrices("Розничная цена");
    }

    private void initTypeOfPrices(String name) {
        new TypeOfPrice(name);
    }
}
