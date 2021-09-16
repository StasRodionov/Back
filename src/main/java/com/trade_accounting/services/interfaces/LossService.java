package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Loss;
import com.trade_accounting.models.dto.LossDto;

public interface LossService extends AbstractService<LossDto>, SearchableService<Loss, LossDto> {
}

