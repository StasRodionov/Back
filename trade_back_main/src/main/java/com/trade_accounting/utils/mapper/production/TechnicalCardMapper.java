package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicalCardMapper {
    //TechnicalCard

    TechnicalCard toModel(TechnicalCardDto technicalCardDto);

    @Mapping(target = "technicalCardGroupId", source = "technicalCardGroup.id")
    @Mapping(target = "finalProductionId", source = "finalProduction")
    @Mapping(target = "materialsId", source = "materials")
    TechnicalCardDto toDto(TechnicalCard technicalCard);

    default Long technicalCardProductionToLong(TechnicalCardProduction technicalCardProduction) {
        return technicalCardProduction.getId();
    }

}
