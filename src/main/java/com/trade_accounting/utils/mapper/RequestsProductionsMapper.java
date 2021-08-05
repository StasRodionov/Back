package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.RequestsProductions;
import com.trade_accounting.models.dto.RequestsProductionsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RequestsProductionsMapper {

    /**
     * @return RequestsProductionsMapper
     */
    @Mappings({
            @Mapping(target = "technicalCard.id", ignore = true),
            @Mapping(target = "warehouseId.id", ignore = true),
            @Mapping(target = "dateOfTheCertificate", ignore = true)
    })

    RequestsProductions toModel(RequestsProductionsDto requestsProductionsDto);

    /**
     * @return RequestsProductionsMapperDto
     */
    @Mappings({
            @Mapping(source = "technicalCard.id", target = "technicalCardId"),
            @Mapping(source = "warehouse.id", target = "warehouseId"),
            @Mapping(source = "dateOfTheCertificate", target = "dateOfTheCertificate", dateFormat = "yyyy-MM-dd")
    })
    RequestsProductionsDto toDto(RequestsProductions requestsProductions);
}
