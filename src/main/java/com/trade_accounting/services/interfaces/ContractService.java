package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ContractService {

    List<ContractDto> getAll();

    List<ContractDto> search(Specification<Contract> specification);

    ContractDto getById(Long id);

    void save(ContractDto contractDto);

    void deleteById(Long id);
}
