package com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.dto.units.ExportDto;
import com.trade_accounting.models.entity.units.Export;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface ExportService extends AbstractService<ExportDto>, SearchableService<Export, ExportDto> {

    List<ExportDto> searchByString(String text);
}
