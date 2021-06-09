package com.trade_accounting.services.impl;

import com.trade_accounting.models.SupplierAccounts;
import com.trade_accounting.models.dto.SupplierAccountsDto;
import com.trade_accounting.repositories.SupplierAccountsRepository;
import com.trade_accounting.services.interfaces.SupplierAccountsService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
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

    public SupplierAccountsImpl(SupplierAccountsRepository supplierAccountsRepository,
                                DtoMapper dtoMapper) {
        this.supplierAccountsRepository = supplierAccountsRepository;
        this.dtoMapper = dtoMapper;
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
        SupplierAccounts saveInvoices = supplierAccountsRepository.save(
                dtoMapper.SupplierAccountsDtoToSupplierAccounts(dto));
        dto.setId(saveInvoices.getId());
        return dtoMapper.SupplierAccountsToSupplierAccountsDto(saveInvoices);
    }

    @Override
    public SupplierAccountsDto update(SupplierAccountsDto dto) {
        SupplierAccounts updateInvoices = supplierAccountsRepository.save(
                dtoMapper.SupplierAccountsDtoToSupplierAccounts(dto));
        return dtoMapper.SupplierAccountsToSupplierAccountsDto(updateInvoices);
    }

    @Override
    public void deleteById(Long id) {
        supplierAccountsRepository.deleteById(id);
    }

    @Override
    public List<SupplierAccountsDto> search(Specification<SupplierAccounts> spec) {
        return executeSearch(supplierAccountsRepository, dtoMapper::SupplierAccountsToSupplierAccountsDto, spec);
    }
}
