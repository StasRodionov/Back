package com.trade_accounting.services.impl;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.services.interfaces.CurrencyService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final DtoMapper dtoMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, DtoMapper dtoMapper) {
        this.currencyRepository = currencyRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<CurrencyDto> getAll() {
        return currencyRepository.findAll()
                .stream()
                .map(dtoMapper::currencyToCurrencyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CurrencyDto> search(Specification<Currency> spec) {
        return currencyRepository.getAll();
    }

    @Override
    public CurrencyDto create(CurrencyDto currencyDto) {
        Currency currency = currencyRepository.save(
                dtoMapper.currencyDtoToCurrency(currencyDto)
        );

        return dtoMapper.currencyToCurrencyDto(currency);
    }

    @Override
    public CurrencyDto update(CurrencyDto currencyDto) {
        return create(currencyDto);
    }

    @Override
    public CurrencyDto getById(Long id) {
        return currencyRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        currencyRepository.deleteById(id);
    }

}
