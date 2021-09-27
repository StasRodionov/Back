package com.trade_accounting.services.interfaces;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.dto.InternalOrderDto;

public interface InternalOrderService extends AbstractService<InternalOrderDto>, SearchableService<InternalOrder, InternalOrderDto> {

}
