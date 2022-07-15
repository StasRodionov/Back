package com.trade_accounting.services.interfaces.units;

import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.models.entity.units.SalesChannel;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;

import java.util.List;

public interface SalesChannelService extends AbstractService<SalesChannelDto>, SearchableService<SalesChannel, SalesChannelDto> {

    List<SalesChannelDto> searchByString(String text);
}
