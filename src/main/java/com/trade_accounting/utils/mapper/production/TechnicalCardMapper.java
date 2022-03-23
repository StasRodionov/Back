package com.trade_accounting.utils.mapper.production;

import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.dto.production.TechnicalCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TechnicalCardMapper {
    //TechnicalCard
    @Mappings({
            @Mapping(target = "technicalCardGroup", ignore = true),
            @Mapping(target = "finalProduction", ignore = true),
            @Mapping(target = "materials", ignore = true),
    })
    TechnicalCard toModel(TechnicalCardDto technicalCardDto);

    TechnicalCardDto toDto(TechnicalCard technicalCard);
}
