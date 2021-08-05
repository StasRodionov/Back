package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.dto.TechnicalCardDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TechnicalCardMapper {

    @Mappings({
            @Mapping(source = "technicalCardGroup.id", target = "technicalCardGroupId"),
            @Mapping(source = "finalProduction.id", target = "finalProductionId"),
            @Mapping(source = "materials.id", target = "materialsId"),
    })
    TechnicalCardDto toDto(TechnicalCard technicalCard);

    @InheritInverseConfiguration
    TechnicalCard toModel(TechnicalCardDto technicalCardDto);

}
