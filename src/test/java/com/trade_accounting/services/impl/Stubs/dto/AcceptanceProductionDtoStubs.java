package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.AcceptanceProductionDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.model.AcceptanceProductionModelStubs;
import com.trade_accounting.utils.mapper.AcceptanceProductionMapper;
import com.trade_accounting.utils.mapper.InternalOrderMapper;
import org.mapstruct.factory.Mappers;

public class AcceptanceProductionDtoStubs {
    private static final AcceptanceProductionMapper mapper = Mappers.getMapper(AcceptanceProductionMapper.class);
    public static AcceptanceProductionDto getAcceptanceProductionDto(Long id) {
        return mapper.toAcceptanceProductionDto(AcceptanceProductionModelStubs.getAcceptanceProduction(id));
    }
}
