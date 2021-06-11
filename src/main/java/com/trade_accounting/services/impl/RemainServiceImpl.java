package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.repositories.RemainRepository;
import com.trade_accounting.services.interfaces.RemainService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RemainServiceImpl implements RemainService {
    
    private final RemainRepository remainRepository;

    private final DtoMapper dtoMapper;

    public RemainServiceImpl (RemainRepository remainRepository, DtoMapper dtoMapper){
        this.remainRepository = remainRepository;
        this.dtoMapper = dtoMapper;
    }

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
