package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Correction;
import com.trade_accounting.models.dto.CorrectionDto;

public interface CorrectionService extends AbstractService<CorrectionDto>, SearchableService<Correction, CorrectionDto> {

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}

