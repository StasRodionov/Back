package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.TechCardDto;
import com.trade_accounting.services.interfaces.TechCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TechCardServiceImpl implements TechCardService { 
    @Override
    public List<TechCardDto> getAll() {
        return null;
    }

    @Override
    public TechCardDto getById(Long id) {
        return null;
    }

    @Override
    public TechCardDto create(TechCardDto dto) {
        return null;
    }

    @Override
    public TechCardDto update(TechCardDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
