package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Inventarization;
import com.trade_accounting.models.dto.InventarizationDto;

import java.util.List;

public interface InventarizationService extends AbstractService<InventarizationDto>, SearchableService<Inventarization, InventarizationDto> {

    List<InventarizationDto> search(String search);

    void moveToRecyclebin(long id);

    void restoreFromRecyclebin(long id);

}
