package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.InventarizationDto;

public interface InventarizationService extends AbstractService<InventarizationDto> {

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}
