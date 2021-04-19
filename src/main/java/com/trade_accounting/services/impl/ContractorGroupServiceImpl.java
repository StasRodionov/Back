package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import com.trade_accounting.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractorGroupServiceImpl implements ContractorGroupService {

    private final ContractorGroupRepository contractorGroupRepository;
    private final DtoMapper dtoMapper;

    @Override
    public List<ContractorGroupDto> getAll() {
        return contractorGroupRepository.getAll();
    }

    @Override
    public ContractorGroupDto getById(Long id) {
        return contractorGroupRepository.getById(id);
    }

    @Override
    public ContractorGroupDto create(ContractorGroupDto dto) {
        contractorGroupRepository
                .save(dtoMapper.contractorGroupDtoToContractorGroup(dto));
        return dto;
    }

    @Override
    public ContractorGroupDto update(ContractorGroupDto dto) {
        create(dto);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        contractorGroupRepository.deleteById(id);
    }
}
