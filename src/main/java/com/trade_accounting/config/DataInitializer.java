package com.trade_accounting.config;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private TypeOfPriceRepository typeOfPriceRepository;

    public DataInitializer(TypeOfPriceRepository typeOfPriceRepository) {
        this.typeOfPriceRepository = typeOfPriceRepository;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
    }

    private void initTypeOfPrices() {
        typeOfPriceRepository.save(new TypeOfPrice("Оптовая цена","1"));
        typeOfPriceRepository.save(new TypeOfPrice("Розничная цена", "2"));
    }
}
