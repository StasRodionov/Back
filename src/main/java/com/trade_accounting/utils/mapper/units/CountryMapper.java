package com.trade_accounting.utils.mapper.units;

import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.models.entity.units.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country toModel(CountryDto countryDto);
    CountryDto toDto(Country country);
}
