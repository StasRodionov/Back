package com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.dto.units.ImportDto;
import com.trade_accounting.models.entity.units.Import;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface ImportService extends AbstractService<ImportDto>, SearchableService<Import, ImportDto> {

    List<ImportDto> searchByString(String text);
}
