package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.MovementDto;

public interface MovementService extends AbstractService<MovementDto> {

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}
