package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.InvoicesStatus;
import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.SupplierAccountRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.SupplierAccountService;
import com.trade_accounting.utils.mapper.CompanyMapper;
import com.trade_accounting.utils.mapper.ContractorMapper;
import com.trade_accounting.utils.mapper.SupplierAccountMapper;
import com.trade_accounting.utils.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierAccountServiceImpl implements SupplierAccountService {

    private final SupplierAccountRepository supplierAccountRepository;
    private final SupplierAccountMapper supplierAccountMapper;
    private final WarehouseMapper warehouseMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyMapper companyMapper;
    private final ContractorMapper contractorMapper;

    @Override
    public List<SupplierAccountDto> getAll() {
        return supplierAccountRepository.getAll().stream()
                .map(supplierAccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SupplierAccountDto getById(Long id) {
        Optional<SupplierAccount> invoicesToCustomers = supplierAccountRepository.findById(id);
        return supplierAccountMapper.toDto(invoicesToCustomers.orElse(new SupplierAccount()));
    }

    @Override
    public SupplierAccountDto create(SupplierAccountDto createSupplier) {
        SupplierAccount invoiceSaved = supplierAccountMapper.toModel(createSupplier);
        Company company = companyRepository.getCompaniesById(createSupplier.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(createSupplier.getContractorId());
        Warehouse warehouse = warehouseRepository.getOne(createSupplier.getWarehouseId());
        Contract contract = contractRepository.getById(createSupplier.getContractId());
        invoiceSaved.setCompany(company);
        invoiceSaved.setContractor(contractor);
        invoiceSaved.setWarehouse(warehouse);
        invoiceSaved.setContract(contract);
        return supplierAccountMapper.toDto(supplierAccountRepository.save(invoiceSaved));
    }

    @Override
    public SupplierAccountDto update(SupplierAccountDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        supplierAccountRepository.deleteById(id);
    }


    @Override
    public List<SupplierAccountDto> searchByString(String nameFilter) {
        if(nameFilter.matches("[0-9]+")) {
            List<SupplierAccountDto> searchForNumber = supplierAccountRepository.searchById(Long.parseLong(nameFilter));
            return searchForNumber;
//        } else if ("null".equals(nameFilter) || nameFilter.isEmpty()) {
//            List<SupplierAccountDto> supplierAccountList = supplierAccountRepository.getAll();
//            return supplierAccountList;
        } else {
            List<SupplierAccountDto> supplierAccountListDto = supplierAccountRepository.searchByNameFilter(nameFilter);
            return supplierAccountListDto;
        }
    }

    @Override
    public List<SupplierAccountDto> search(Specification<SupplierAccount> spec) {
        return executeSearch(supplierAccountRepository, supplierAccountMapper::toDto, spec);
    }

    @Override
    public List<SupplierAccountDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice) {
        List<SupplierAccountDto> invoiceDtoList = supplierAccountRepository.findBySearchAndTypeOfInvoice(search, typeOfInvoice);
        for (SupplierAccountDto invoice : invoiceDtoList) {
            invoice.setCompanyId(companyMapper.toDto(companyRepository.getCompaniesById(invoice.getCompanyId())).getId());
            invoice.setContractorId(contractorMapper.contractorToContractorDto(
                    contractorRepository.getOne(invoice.getContractorId())).getId());
            invoice.setWarehouseId(warehouseRepository.getById(invoice.getWarehouseId()).getId());
            invoice.setContractId(contractRepository.getById(invoice.getContractId()).getId());

        }
        return invoiceDtoList;
    }

    @Override
    public void moveToRecyclebin(long id) {
        SupplierAccount supplierAccount = supplierAccountRepository.getOne(id);
        supplierAccount.setIsRecyclebin(true);
        supplierAccountRepository.save(supplierAccount);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        SupplierAccount supplierAccount = supplierAccountRepository.getOne(id);
        supplierAccount.setIsRecyclebin(false);
        supplierAccountRepository.save(supplierAccount);
    }
}
