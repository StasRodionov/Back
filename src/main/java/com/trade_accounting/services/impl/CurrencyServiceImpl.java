package com.trade_accounting.services.impl;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.services.interfaces.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDto> getAll() {
        return currencyRepository.getAll();
    }

    @Override
    public List<CurrencyDto> search(Specification<Currency> spec) {
        return currencyRepository.findAll(spec).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CurrencyDto getById(Long id) {
        return currencyRepository.getById(id);
    }

    @Override
    public void create(CurrencyDto currencyDto) {
        Currency currency = new Currency(null,
                currencyDto.getShortName(),
                currencyDto.getFullName(),
                currencyDto.getDigitalCode(),
                currencyDto.getLetterCode(),
                currencyDto.getSortNumber());
        currencyRepository.save(currency);
    }

    @Override
    public void update(CurrencyDto currencyDto) {
        Currency currency = new Currency(currencyDto.getId(),
                currencyDto.getShortName(),
                currencyDto.getFullName(),
                currencyDto.getDigitalCode(),
                currencyDto.getLetterCode(),
                currencyDto.getSortNumber());
        currencyRepository.save(currency);
    }

    @Override
    public void deleteById(Long id) {
        currencyRepository.deleteById(id);
    }

    private CurrencyDto convertToDto(Currency currency) {
        CurrencyDto currencyDto = modelMapper.map(currency, CurrencyDto.class);
        return currencyDto;
    }
}
