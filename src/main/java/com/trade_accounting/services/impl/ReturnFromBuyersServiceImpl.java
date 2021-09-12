package com.trade_accounting.services.impl;

import com.trade_accounting.models.ReturnFromBuyers;
import com.trade_accounting.models.dto.ReturnFromBuyersDto;
import com.trade_accounting.repositories.ReturnFromBuyersRepository;
import com.trade_accounting.services.interfaces.ReturnFromBuyersService;
import com.trade_accounting.utils.mapper.ReturnFromBuyersMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnFromBuyersServiceImpl implements ReturnFromBuyersService {

    private final ReturnFromBuyersRepository returnFromBuyersRepository;
    private final ReturnFromBuyersMapperImpl returnFromBuyersMapper;



    @Override
    public List<ReturnFromBuyersDto> getAll() {
        return returnFromBuyersRepository.getAll();
    }

    @Override
    public ReturnFromBuyersDto getById(Long id) {
        return returnFromBuyersRepository.getById(id);
    }

    @Override
    public ReturnFromBuyersDto create(ReturnFromBuyersDto dto) {
        return null;
    }

    @Override
    public ReturnFromBuyersDto update(ReturnFromBuyersDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        returnFromBuyersRepository.deleteById(id);
    }

    @Override
    public List<ReturnFromBuyersDto> search(Specification<ReturnFromBuyers> spec) {
        return null;
    }
}
