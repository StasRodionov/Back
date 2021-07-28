package com.trade_accounting.services.impl;

import com.trade_accounting.models.ContractorStatus;
import com.trade_accounting.models.dto.ContractorStatusDto;
import com.trade_accounting.repositories.ContractorStatusRepository;
import com.trade_accounting.services.interfaces.ContractorStatusService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContractorStatusServiceImpl implements ContractorStatusService {

    private final ContractorStatusRepository contractorStatusRepository;

    private final DtoMapper dtoMapper;

    public ContractorStatusServiceImpl(ContractorStatusRepository contractorStatusRepository, DtoMapper dtoMapper) {
        this.contractorStatusRepository = contractorStatusRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public ContractorStatusDto getById(Long id) {
        return dtoMapper.statusToStatusDto(contractorStatusRepository.getOne(id));
    }

    @Override
    public ContractorStatusDto create(ContractorStatusDto dto) {
        return dtoMapper.statusToStatusDto(contractorStatusRepository.save(dtoMapper.statusDtoToStatus(dto)));
    }

    @Override
    public ContractorStatusDto update(ContractorStatusDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        contractorStatusRepository.deleteById(id);
    }

    @Override
    public List<ContractorStatusDto> getAll() {
        return contractorStatusRepository.findAll().stream()
                .map(dtoMapper::statusToStatusDto)
                .collect(Collectors.toList());
    }
}
