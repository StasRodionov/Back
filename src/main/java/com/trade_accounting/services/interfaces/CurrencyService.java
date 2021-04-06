package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> getAll();

    List<CurrencyDto> search(Specification<Currency> specification);

    CurrencyDto getById(Long id);

    CurrencyDto create(CurrencyDto currencyDto);

    CurrencyDto update(CurrencyDto currencyDto);

    void deleteById(Long id);
}
