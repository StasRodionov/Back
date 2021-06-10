package com.trade_accounting.services.impl;

import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.SupplierAccountRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.SupplierAccountService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierAccountImpl implements SupplierAccountService {

    private final SupplierAccountRepository supplierAccountRepository;
    private final DtoMapper dtoMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;

    public SupplierAccountImpl(SupplierAccountRepository supplierAccountRepository,
                               DtoMapper dtoMapper, CompanyRepository companyRepository, ContractorRepository contractorRepository, ContractRepository contractRepository, WarehouseRepository warehouseRepository) {
        this.supplierAccountRepository = supplierAccountRepository;
        this.dtoMapper = dtoMapper;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.contractRepository = contractRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<SupplierAccountDto> getAll() {
        return supplierAccountRepository.findAll().stream()
                .map(dtoMapper::SupplierAccountToSupplierAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierAccountDto getById(Long id) {
        Optional<SupplierAccount> invoicesToCustomers = supplierAccountRepository.findById(id);
        return dtoMapper.SupplierAccountToSupplierAccountDto(invoicesToCustomers.orElse(new SupplierAccount()));
    }

    @Override
    public SupplierAccountDto create(SupplierAccountDto createSupplier) {
        SupplierAccount saveInvoices = SupplierAccount.builder().id(createSupplier.getId()).date(createSupplier.getDate())
                .comment(createSupplier.getComment()).isSpend(createSupplier.isSpend())
                .company(dtoMapper.companyDtoToCompany(companyRepository.getById(createSupplier.getCompanyId())))
                .warehouse(dtoMapper.warehouseDtoToWarehouse(warehouseRepository.getById(createSupplier.getWarehouseId())))
                .contract(dtoMapper.contractDtoToContract(contractRepository.getById(createSupplier.getContractId())))
                .contractor((contractorRepository.getOne(createSupplier.getContractorId())))
                .build();
        return dtoMapper.SupplierAccountToSupplierAccountDto(supplierAccountRepository.save(saveInvoices));
    }

    @Override
    public SupplierAccountDto update(SupplierAccountDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        supplierAccountRepository.deleteById(id);
    }


}
