package com.trade_accounting.services.interfaces.finance;

import com.trade_accounting.models.dto.finance.FunnelDto;
import com.trade_accounting.models.entity.finance.Funnel;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

public interface FunnelService extends AbstractService<FunnelDto>, SearchableService<Funnel, FunnelDto> {
}
