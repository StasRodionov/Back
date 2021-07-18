package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.MovementProductDto;
import com.trade_accounting.services.impl.Stubs.model.MovementProductModelStubs;
import com.trade_accounting.utils.mapper.MovementProductMapper;
import org.mapstruct.factory.Mappers;

public class MovementProductDtoStubs {

    private static final MovementProductMapper mapper = Mappers.getMapper(MovementProductMapper.class);

    public static MovementProductDto getDto(Long id) {
        return mapper.toMovementProductDto(MovementProductModelStubs.getMovementProduct(id));
    }
}
