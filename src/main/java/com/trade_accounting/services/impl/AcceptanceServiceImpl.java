package com.trade_accounting.services.impl;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.repositories.AcceptanceRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.AcceptanceService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptanceServiceImpl implements AcceptanceService {

    private final AcceptanceRepository acceptanceRepository;

    private final ContractRepository contractRepository;

    private final ContractorRepository contractorRepository;

    private final ProjectRepository projectRepository;

    private final WarehouseRepository warehouseRepository;

    private final DtoMapper dtoMapper;

    public AcceptanceServiceImpl(AcceptanceRepository acceptanceRepository, ContractRepository contractRepository, ContractorRepository contractorRepository, ProjectRepository projectRepository, WarehouseRepository warehouseRepository, DtoMapper dtoMapper) {
        this.acceptanceRepository = acceptanceRepository;
        this.contractRepository = contractRepository;
        this.contractorRepository = contractorRepository;
        this.projectRepository = projectRepository;
        this.warehouseRepository = warehouseRepository;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public List<AcceptanceDto> getAll() {
        List<Acceptance> acceptanceList = acceptanceRepository.findAll();
        return acceptanceList.stream().map(dtoMapper::acceptanceToAcceptanceDto).collect(Collectors.toList());
    }

    @Override
    public AcceptanceDto getById(Long id) {
        return dtoMapper.acceptanceToAcceptanceDto(acceptanceRepository.getOne(id));
    }

    @Override
    public AcceptanceDto create(AcceptanceDto dto) {
        Acceptance acceptance = dtoMapper.acceptanceDtoToAcceptance(dto);
        acceptance.setContract(contractRepository.getOne(dto.getContractId()));
        acceptance.setContractor(contractorRepository.getOne(dto.getContractorId()));
        acceptance.setProject(projectRepository.getOne(dto.getProjectId()));
        acceptance.setWarehouse(warehouseRepository.getOne(dto.getWarehouseId()));
        return dtoMapper.acceptanceToAcceptanceDto(acceptanceRepository.save(acceptance));
    }

    @Override
    public AcceptanceDto update(AcceptanceDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        acceptanceRepository.deleteById(id);
    }

    @Override
    public List<AcceptanceDto> searchByNumberAndComment(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            return acceptanceRepository.findAll().stream()
                    .map(dtoMapper::acceptanceToAcceptanceDto)
                    .collect(Collectors.toList());
        } else {
            return acceptanceRepository.search(searchTerm).stream()
                    .map(dtoMapper::acceptanceToAcceptanceDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<AcceptanceDto> search(Specification<Acceptance> spec) {
        return executeSearch(acceptanceRepository, dtoMapper::acceptanceToAcceptanceDto, spec);
    }
}
