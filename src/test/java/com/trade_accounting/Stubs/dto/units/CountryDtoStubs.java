package com.trade_accounting.Stubs.dto.units;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.utils.mapper.units.CountryMapper;
import org.mapstruct.factory.Mappers;

public class CountryDtoStubs {
    private static final CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
    public static CountryDto getCountryDto(Long id) {
        return countryMapper.toDto(ModelStubs.getCountry(id));
    }
}
