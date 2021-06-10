package com.trade_accounting.services.impl;

import com.trade_accounting.models.SupplierAccounts;
import com.trade_accounting.models.dto.SupplierAccountsDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.SupplierAccountsRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.SupplierAccountsService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierAccountsImpl implements SupplierAccountsService {

    private final SupplierAccountsRepository supplierAccountsRepository;
    private final DtoMapper dtoMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;

    public SupplierAccountsImpl(SupplierAccountsRepository supplierAccountsRepository,
                                DtoMapper dtoMapper, CompanyRepository companyRepository, ContractorRepository contractorRepository, ContractRepository contractRepository, WarehouseRepository warehouseRepository) {
        this.supplierAccountsRepository = supplierAccountsRepository;
        this.dtoMapper = dtoMapper;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.contractRepository = contractRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<SupplierAccountsDto> getAll() {
        return supplierAccountsRepository.findAll().stream()
                .map(dtoMapper::SupplierAccountsToSupplierAccountsDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierAccountsDto getById(Long id) {
        Optional<SupplierAccounts> invoicesToCustomers = supplierAccountsRepository.findById(id);
        return dtoMapper.SupplierAccountsToSupplierAccountsDto(invoicesToCustomers.orElse(new SupplierAccounts()));
    }

    @Override
    public SupplierAccountsDto create(SupplierAccountsDto dto) {
        SupplierAccounts saveInvoices = SupplierAccounts.builder().id(dto.getId()).date(dto.getDate())
                .comment(dto.getComment())
                .company(dtoMapper.companyDtoToCompany(companyRepository.getById(dto.getNameCompany())))
                .warehouse(dtoMapper.warehouseDtoToWarehouse(warehouseRepository.getById(dto.getNameWarehouse())))
                .contract(dtoMapper.contractDtoToContract(contractRepository.getById(dto.getNumberContract())))
                .contractor((contractorRepository.getOne(dto.getNameContractor())))
                .build();
        return dtoMapper.SupplierAccountsToSupplierAccountsDto(supplierAccountsRepository.save(saveInvoices));
    }

    @Override
    public SupplierAccountsDto update(SupplierAccountsDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        supplierAccountsRepository.deleteById(id);
    }


}
