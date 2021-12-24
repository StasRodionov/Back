package com.trade_accounting.services.interfaces;


import com.trade_accounting.models.ProductionTargets;
import com.trade_accounting.models.dto.ProductionTargetsDto;
import java.util.List;

public interface ProductionTargetsService extends AbstractService<ProductionTargetsDto>, SearchableService<ProductionTargets,
        ProductionTargetsDto>{

    List<ProductionTargetsDto> search(String searchTerm);

}
