package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.utils.mapper.util.TechnicalCardProductionToLongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TechnicalCardProductionToLongMapper.class})
public interface TechnicalCardMapper {
    //TechnicalCard
    @Mapping(target = "technicalCardGroup", ignore = true)
    @Mapping(target = "finalProduction", ignore = true)
    @Mapping(target = "materials", ignore = true)
    TechnicalCard toModel(TechnicalCardDto technicalCardDto);

    @Mapping(target = "technicalCardGroupId", source = "technicalCardGroup.id")
    @Mapping(target = "finalProductionId", source = "finalProduction")
    @Mapping(target = "materialsId", source = "materials")
    TechnicalCardDto toDto(TechnicalCard technicalCard);
}
