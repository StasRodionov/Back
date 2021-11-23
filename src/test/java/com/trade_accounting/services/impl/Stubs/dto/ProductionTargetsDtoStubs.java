package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ProductionTargetsDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.ProductionTargetsMapper;
import org.mapstruct.factory.Mappers;

public class ProductionTargetsDtoStubs {
    private static final ProductionTargetsMapper mapper = Mappers.getMapper(ProductionTargetsMapper.class);
    public static ProductionTargetsDto getProductionTargetsDto(Long id) {
        return mapper.toDto(ModelStubs.getProductionTargets(id));
    }
}
