package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.RetailSalesRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.interfaces.RetailSalesService;
import com.trade_accounting.utils.mapper.RetailSalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RetailSalesServiceImpl implements RetailSalesService {

    private final RetailSalesRepository retailSalesRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final ContractorRepository contractorRepository;
    private final CompanyRepository companyRepository;
    private final RetailSalesMapper retailSalesMapper;

    @Override
    public List<RetailSalesDto> getAll() {
        return retailSalesRepository.findAll().stream()
                .map(retailSalesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailSalesDto getById(Long id) {
        Optional<RetailSales> retailSales = retailSalesRepository.findById(id);
        return retailSalesMapper.toDto(
                retailSales.orElse(new RetailSales()));
    }

    @Override
    public RetailSalesDto create(RetailSalesDto dto) {
        RetailSales retailSales = retailSalesMapper.toModel(dto);
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Optional<RetailStore> retailStore = retailStoreRepository.findById(dto.getRetailStoreId());
        Contractor contactor = contractorRepository.getContractorById(dto.getContractorId());

        retailSales.setCompany(company);
        retailSales.setRetailStore(retailStore.orElse(null));
        retailSales.setContractor(contactor);

        return retailSalesMapper.toDto(retailSalesRepository.save(retailSales));
    }

    @Override
    public RetailSalesDto update(RetailSalesDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailSalesRepository.deleteById(id);
    }
}
