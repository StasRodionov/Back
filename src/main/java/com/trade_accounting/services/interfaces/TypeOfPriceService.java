package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeOfPriceService {

    List<TypeOfPriceDto> getAll();

    TypeOfPriceDto getById(@Param("id") Long id);

    void create(@Param("t") TypeOfPriceDto typeOfPriceDto);

    void update(TypeOfPriceDto typeOfPriceDto);

    void deleteById(@Param("id") Long id);
}
