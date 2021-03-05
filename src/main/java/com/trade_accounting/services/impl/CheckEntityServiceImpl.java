package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import org.springframework.stereotype.Service;

@Service
public class CheckEntityServiceImpl implements CheckEntityService {
    private final UnitRepository unitRepository;

    public CheckEntityServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }


    @Override
    public void checkExistUnitById(Long unitId) {
        if(unitRepository.existsById(unitId)) {
            throw new NotFoundEntityException("Ед. измерения с id=" + unitId + ", не найдена");
        }
    }
}
