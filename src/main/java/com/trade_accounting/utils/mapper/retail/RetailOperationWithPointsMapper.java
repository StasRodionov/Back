package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;
import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailOperationWithPointsMapper {
    //RetailOperationWithPoints
    @Mapping(source = "bonusProgramId", target = "bonusProgram.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    RetailOperationWithPoints toModel(RetailOperationWithPointsDto dto);

    @Mapping(source = "bonusProgram.id", target = "bonusProgramId")
    @Mapping(source = "contractor.id", target = "contractorId")
    RetailOperationWithPointsDto toDto(RetailOperationWithPoints model);
}
