package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CurrencyService extends AbstractService<CurrencyDto> {

    List<CurrencyDto> search(Specification<Currency> specification);
}
