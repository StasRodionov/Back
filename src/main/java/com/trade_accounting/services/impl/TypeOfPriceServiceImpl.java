package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeOfPriceServiceImpl implements TypeOfPriceService {

    private final TypeOfPriceRepository typeOfPriceRepository;

    public TypeOfPriceServiceImpl(TypeOfPriceRepository typeOfPriceRepository) {
        this.typeOfPriceRepository = typeOfPriceRepository;
    }
}
