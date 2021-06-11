package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.services.interfaces.RemainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RemainServiceImpl implements RemainService {
    @Override
    public List<RemainDto> getAll() {
        return null;
    }

    @Override
    public RemainDto getById(Long id) {
        return null;
    }

    @Override
    public RemainDto create(RemainDto dto) {
        return null;
    }

    @Override
    public RemainDto update(RemainDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
