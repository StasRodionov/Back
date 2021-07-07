package com.trade_accounting.services.impl;

import com.trade_accounting.models.BalanceAdjustment;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import com.trade_accounting.repositories.BalanceAdjustmentRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.services.interfaces.BalanceAdjustmentService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BalanceAdjustmentServiceImpl implements BalanceAdjustmentService {
    private final BalanceAdjustmentRepository balanceAdjustmentsRepository;
    private final DtoMapper dtoMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;

    public BalanceAdjustmentServiceImpl(BalanceAdjustmentRepository balanceAdjustmentsRepository,
                                       DtoMapper dtoMapper, CompanyRepository companyRepository,
                                       ContractorRepository contractorRepository) {
        this.balanceAdjustmentsRepository = balanceAdjustmentsRepository;
        this.dtoMapper = dtoMapper;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;

    }

    @Override
    public List<BalanceAdjustmentDto> getAll() {
        return balanceAdjustmentsRepository.getAll();
    }

    @Override
    public BalanceAdjustmentDto getById(Long id) {
        Optional<BalanceAdjustment> balanceAdjustmentsById = balanceAdjustmentsRepository.findById(id);
        return dtoMapper.balanceAdjustmentToBalanceAdjustmentDto(balanceAdjustmentsById.orElse(new BalanceAdjustment()));
    }

    @Override
    public BalanceAdjustmentDto create(BalanceAdjustmentDto dto) {
        BalanceAdjustment balanceAdjustments = BalanceAdjustment.builder().id(dto.getId())
                .date(dto.getDate())
                .contractor(contractorRepository.getOne(dto.getContractorId()))
                .company(companyRepository.getCompaniesById(dto.getCompanyId()))
                .account(dto.getAccount())
                .cashOffice(dto.getCashOffice())
                .comment(dto.getComment())
                .dateChanged(dto.getDateChanged())
                .whoChanged(dto.getWhoChanged())
                .build();
        return dtoMapper.balanceAdjustmentToBalanceAdjustmentDto(balanceAdjustmentsRepository.save(balanceAdjustments));
    }

    @Override
    public BalanceAdjustmentDto update(BalanceAdjustmentDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        balanceAdjustmentsRepository.deleteById(id);
    }

    @Override
    public List<BalanceAdjustmentDto> searchByString(String nameFilter) {
        if (nameFilter.matches("[0-9]+")) {
            return balanceAdjustmentsRepository.searchById(Long.parseLong(nameFilter));
        } else if (nameFilter.equals("null") || nameFilter.isEmpty()) {
            return balanceAdjustmentsRepository.getAll();
        } else {
            return balanceAdjustmentsRepository.searchByNameFilter(nameFilter);
        }
    }

    @Override
    public List<BalanceAdjustmentDto> search(Specification<BalanceAdjustment> spec) {
        return executeSearch(balanceAdjustmentsRepository, dtoMapper::balanceAdjustmentToBalanceAdjustmentDto, spec);
    }
}