package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.CorrectionDto;

public interface CorrectionService extends AbstractService<CorrectionDto> {

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}

