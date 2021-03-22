package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.TaxSystemDto;
import java.util.List;

public interface TaxSystemService {

    List<TaxSystemDto> getAll();

    TaxSystemDto getById(Long id);

    TaxSystemDto create(TaxSystemDto dto);

    TaxSystemDto update(TaxSystemDto dto);

    void deleteById(Long id);

}
