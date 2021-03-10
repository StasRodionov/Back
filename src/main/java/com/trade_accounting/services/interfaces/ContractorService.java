package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ContractorService {

    List<ContractorDto> searchContractor(Specification<Contractor> specification);

    List<ContractorDto> getAll(String searchTerm);
    List<ContractorDto> getAllLite();

    List<ContractorDto> getAll();

    ContractorDto getById(Long id);

    void create(ContractorDto contractorDto);

    void update(ContractorDto contractorDto);

    void deleteById(Long id);
}
