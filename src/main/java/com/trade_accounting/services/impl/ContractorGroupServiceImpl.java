package com.trade_accounting.services.impl;

import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ContractorGroupServiceImpl implements ContractorGroupService {

    private final ContractorGroupRepository contractorGroupRepository;

    public ContractorGroupServiceImpl(ContractorGroupRepository contractorGroupRepository) {
        this.contractorGroupRepository = contractorGroupRepository;
    }

    @Override
    public List<ContractorGroupDto> getAll() {
        return contractorGroupRepository.getAll();
    }

    @Override
    public ContractorGroupDto getById(Long id) {
        return contractorGroupRepository.getById(id);
    }

    @Override
    public void create(ContractorGroupDto dto) {
        contractorGroupRepository.save(new ContractorGroup(
                dto.getName(),
                dto.getSortNumber())
        );
    }

    @Override
    public void update(ContractorGroupDto dto) {
        contractorGroupRepository.save(new ContractorGroup(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber())
        );
    }

    @Override
    public void deleteById(Long id) {
        contractorGroupRepository.deleteById(id);
    }
}
