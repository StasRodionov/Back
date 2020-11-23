package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.services.interfaces.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
