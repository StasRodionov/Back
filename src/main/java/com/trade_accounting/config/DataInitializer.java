package com.trade_accounting.config;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final TypeOfPriceService typeOfPriceService;

    public DataInitializer(TypeOfPriceService typeOfPriceService) {
        this.typeOfPriceService = typeOfPriceService;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto("Оптовая цена","1"));
        typeOfPriceService.create(new TypeOfPriceDto("Розничная цена", "2"));
    }
}
