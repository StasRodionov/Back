package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.StagesProductionDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.StagesProductionMapper;
import org.mapstruct.factory.Mappers;

public class StagesProductionDtoStubs {
    private static final StagesProductionMapper mapper = Mappers.getMapper(StagesProductionMapper.class);
    public static StagesProductionDto getStagesProductionDto(Long id) {
        return mapper.toDto(ModelStubs.getStagesProduction(id));
    }
}
