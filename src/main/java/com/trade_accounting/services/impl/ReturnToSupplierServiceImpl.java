package com.trade_accounting.services.impl;

import com.trade_accounting.models.ReturnToSupplier;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ReturnToSupplierRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.ReturnToSupplierService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.mapper.ReturnToSupplierMapper;
import com.trade_accounting.utils.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnToSupplierServiceImpl implements ReturnToSupplierService {

    private final ReturnToSupplierRepository returnsToSuppliersRepository;
    private final DtoMapper dtoMapper;
    private final WarehouseMapper warehouseMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;
    private final ReturnToSupplierMapper returnToSupplierMapper;

    @Override
    public List<ReturnToSupplierDto> getAll() {
        return returnsToSuppliersRepository.getAll();
    }

    @Override
    public ReturnToSupplierDto getById(Long id) {
        Optional<ReturnToSupplier> returnsToSuppliersById = returnsToSuppliersRepository.findById(id);
        return returnToSupplierMapper.toDto(returnsToSuppliersById.orElse(new ReturnToSupplier()));
    }

    @Override
    public ReturnToSupplierDto create(ReturnToSupplierDto dto) {
        ReturnToSupplier returnsToSuppliers = ReturnToSupplier.builder().id(dto.getId())
                .date(dto.getDate())
                .contractor(contractorRepository.getOne(dto.getContractorId()))
                .contract(dtoMapper.contractDtoToContract(contractRepository.getById(dto.getContractId())))
                .warehouse(warehouseMapper.toModel(warehouseRepository.getById(dto.getWarehouseId())))
                .company(companyRepository.getCompaniesById(dto.getCompanyId()))
                .comment(dto.getComment())
                .isPrint(dto.getIsPrint())
                .isSend(dto.getIsSend())
                .build();
        return returnToSupplierMapper.toDto(returnsToSuppliersRepository.save(returnsToSuppliers));
    }

    @Override
    public ReturnToSupplierDto update(ReturnToSupplierDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        returnsToSuppliersRepository.deleteById(id);
    }

    @Override
    public List<ReturnToSupplierDto> searchByString(String nameFilter) {
        if (nameFilter.matches("[0-9]+")) {
            return returnsToSuppliersRepository.searchById(Long.parseLong(nameFilter));
        } else if (nameFilter.equals("null") || nameFilter.isEmpty()) {
            return returnsToSuppliersRepository.getAll();
        } else {
            return returnsToSuppliersRepository.searchByNameFilter(nameFilter);
        }
    }

    @Override
    public List<ReturnToSupplierDto> search(Specification<ReturnToSupplier> spec) {
        return executeSearch(returnsToSuppliersRepository, returnToSupplierMapper::toDto, spec);
    }
}
