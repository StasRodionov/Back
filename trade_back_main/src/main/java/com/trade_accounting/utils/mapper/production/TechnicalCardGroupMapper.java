package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalCardGroup;
import com.trade_accounting.models.dto.production.TechnicalCardGroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalCardGroupMapper {
    //TechnicalCardGroup
    TechnicalCardGroup toModel(TechnicalCardGroupDto technicalCardGroupDto);

    TechnicalCardGroupDto toDto(TechnicalCardGroup technicalCardGroup);
}
