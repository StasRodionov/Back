package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.OperationsAbstract;
import com.trade_accounting.models.dto.OperationsDto;

import java.util.List;

public interface OperationsService extends AbstractService<OperationsDto>,
        SearchableService<OperationsAbstract, OperationsDto>{

    List<OperationsDto> quickSearch(String text);

    List<OperationsDto> quickSearchRecycle(String text);

}