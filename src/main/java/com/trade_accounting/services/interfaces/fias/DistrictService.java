package com.trade_accounting.services.interfaces.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;

import java.util.List;

public interface DistrictService {
    List<DistrictDto> getAll();

    DistrictDto getById(Integer id);

    DistrictDto create(DistrictDto districtDto);

    DistrictDto update(DistrictDto districtDto);

    void deleteById(Integer id);
}
