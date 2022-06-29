package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfAccountingDto;
import com.trade_accounting.services.interfaces.warehouse.TypeOfAccountingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeOfAccountingServiceImpl implements TypeOfAccountingService {
    @Override
    public List<TypeOfAccountingDto> getAll() {
        return null;
    }

    @Override
    public TypeOfAccountingDto getById(Long id) {
        return null;
    }

    @Override
    public TypeOfAccountingDto create(TypeOfAccountingDto dto) {
        return null;
    }

    @Override
    public TypeOfAccountingDto update(TypeOfAccountingDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
