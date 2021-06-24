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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReturnToSupplierServiceImpl implements ReturnToSupplierService {

    private final ReturnToSupplierRepository returnsToSuppliersRepository;
    private final DtoMapper dtoMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;

    public ReturnToSupplierServiceImpl(ReturnToSupplierRepository returnsToSuppliersRepository,
                                       DtoMapper dtoMapper, CompanyRepository companyRepository,
                                       ContractorRepository contractorRepository, ContractRepository contractRepository, WarehouseRepository warehouseRepository) {
        this.returnsToSuppliersRepository = returnsToSuppliersRepository;
        this.dtoMapper = dtoMapper;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.contractRepository = contractRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<ReturnToSupplierDto> getAll() {
        return returnsToSuppliersRepository.getAll();
    }

    @Override
    public ReturnToSupplierDto getById(Long id) {
        Optional<ReturnToSupplier> returnsToSuppliersById = returnsToSuppliersRepository.findById(id);
        return dtoMapper.ReturnToSupplierToReturnToSupplierDto(returnsToSuppliersById.orElse(new ReturnToSupplier()));
    }

    @Override
    public ReturnToSupplierDto create(ReturnToSupplierDto dto) {
        ReturnToSupplier returnsToSuppliers = ReturnToSupplier.builder().id(dto.getId())
                .date(dto.getDate())
                .contractor(contractorRepository.getOne(dto.getContractorId()))
                .contract(dtoMapper.contractDtoToContract(contractRepository.getById(dto.getContractId())))
                .warehouse(dtoMapper.warehouseDtoToWarehouse(warehouseRepository.getById(dto.getWarehouseId())))
                .company(dtoMapper.companyDtoToCompany(companyRepository.getCompanyById(dto.getCompanyId())))
                .comment(dto.getComment())
                .isPrint(dto.getIsPrint())
                .isSend(dto.getIsSend())
                .build();
        return dtoMapper.ReturnToSupplierToReturnToSupplierDto(returnsToSuppliersRepository.save(returnsToSuppliers));
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
        return executeSearch(returnsToSuppliersRepository, dtoMapper::ReturnToSupplierToReturnToSupplierDto, spec);
    }
}
