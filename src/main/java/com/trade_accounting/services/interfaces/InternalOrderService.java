package com.trade_accounting.services.interfaces;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.dto.InternalOrderDto;

import java.util.List;

public interface InternalOrderService extends AbstractService<InternalOrderDto>, SearchableService<InternalOrder, InternalOrderDto> {
    List<InternalOrderDto> getAll(String searchItem);
    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);
}
