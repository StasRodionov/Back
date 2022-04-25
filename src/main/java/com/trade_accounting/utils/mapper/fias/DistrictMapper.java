package com.trade_accounting.utils.mapper.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;
import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.fias.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
    //District
    @Mapping(source = "regionDto", target = "region")
    @Mapping(source = "citiesDto", target = "cities")
    District toModel(DistrictDto districtDto);

    @Mapping(source = "region", target = "regionDto")
    @Mapping(source = "cities", target = "citiesDto")
    DistrictDto toDto(District district);

}
