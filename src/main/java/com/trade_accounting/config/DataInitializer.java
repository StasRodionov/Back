package com.trade_accounting.config;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private TypeOfPriceService typeOfPriceService;

    public DataInitializer(TypeOfPriceService typeOfPriceService) {
        this.typeOfPriceService = typeOfPriceService;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto(1L,"Оптовая цена","1"));
        typeOfPriceService.create(new TypeOfPriceDto(2L, "Розничная цена", "2"));
    }
}
