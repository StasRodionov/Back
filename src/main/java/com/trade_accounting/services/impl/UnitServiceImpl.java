package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.UnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }
}
