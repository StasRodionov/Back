package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;

public interface ContractService extends AbstractService<ContractDto>, SearchableService<Contract, ContractDto> {

}
