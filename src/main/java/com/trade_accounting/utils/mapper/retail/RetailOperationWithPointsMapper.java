package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;
import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailOperationWithPointsMapper {
    //RetailOperationWithPoints
    RetailOperationWithPoints toModel(RetailOperationWithPointsDto dto);

    RetailOperationWithPointsDto toDto(RetailOperationWithPoints model);
}
