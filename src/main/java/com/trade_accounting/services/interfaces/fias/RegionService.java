package com.trade_accounting.services.interfaces.fias;

import com.trade_accounting.models.dto.fias.RegionDto;

import java.util.List;

public interface RegionService {

    List<RegionDto> getAll();

    RegionDto getById(Integer id);

    RegionDto create(RegionDto regionDto);

    RegionDto update(RegionDto regionDto);

    void deleteById(Integer id);
}
