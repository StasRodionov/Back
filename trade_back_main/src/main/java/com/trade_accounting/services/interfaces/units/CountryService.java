package com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.models.entity.units.Country;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface CountryService extends AbstractService<CountryDto>, SearchableService<Country, CountryDto> {

    List<CountryDto> searchByString(String text);
}
