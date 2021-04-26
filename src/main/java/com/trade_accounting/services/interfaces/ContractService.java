package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ContractService extends AbstractService<ContractDto> {

    List<ContractDto> search(Specification<Contract> specification);
}
