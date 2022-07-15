package com.trade_accounting.services.interfaces.retail;

import com.trade_accounting.models.dto.retail.RetailEventLogDto;
import com.trade_accounting.models.entity.retail.RetailEventLog;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface RetailEventLogService extends AbstractService<RetailEventLogDto>, SearchableService<RetailEventLog, RetailEventLogDto> {
}
