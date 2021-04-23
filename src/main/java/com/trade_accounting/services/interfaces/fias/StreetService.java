package com.trade_accounting.services.interfaces.fias;

import com.trade_accounting.models.dto.fias.StreetDto;

import java.util.List;

public interface StreetService {
    List<StreetDto> getAll();

    StreetDto getById(Integer id);

    StreetDto create(StreetDto streetDto);

    StreetDto update(StreetDto streetDto);

    void deleteById(Integer id);
}
