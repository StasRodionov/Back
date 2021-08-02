package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.dto.TechnicalCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TechnicalCardMapper {
    //TechnicalCard
    @Mappings({
            @Mapping(source = "technicalCardGroup", target = "technicalCardGroupDto"),
            @Mapping(source = "finalProduction", target = "finalProductionDto"),
            @Mapping(source = "materials", target = "materialsDto"),
    })
    TechnicalCardDto toDto(TechnicalCard technicalCard);

    @Mappings({
            @Mapping(source = "technicalCardGroupDto", target = "technicalCardGroup"),
            @Mapping(source = "finalProductionDto", target = "finalProduction"),
            @Mapping(source = "materialsDto", target = "materials"),
    })
    TechnicalCard toModel(TechnicalCardDto technicalCardDto);

}
