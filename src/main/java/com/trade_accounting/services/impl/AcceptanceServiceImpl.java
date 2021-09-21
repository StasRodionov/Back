package com.trade_accounting.services.impl;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.repositories.AcceptanceRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.AcceptanceService;
import com.trade_accounting.utils.mapper.AcceptanceMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptanceServiceImpl implements AcceptanceService {

    private final AcceptanceRepository acceptanceRepository;

    private final ContractRepository contractRepository;

    private final ContractorRepository contractorRepository;

    private final CompanyRepository companyRepository;

    private final ProjectRepository projectRepository;

    private final WarehouseRepository warehouseRepository;

    private final AcceptanceMapper acceptanceMapper;

    public AcceptanceServiceImpl(AcceptanceRepository acceptanceRepository,
                                 ContractRepository contractRepository,
                                 ContractorRepository contractorRepository,
                                 CompanyRepository companyRepository, ProjectRepository projectRepository,
                                 WarehouseRepository warehouseRepository,
                                 AcceptanceMapper acceptanceMapper) {
        this.acceptanceRepository = acceptanceRepository;
        this.contractRepository = contractRepository;
        this.contractorRepository = contractorRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.warehouseRepository = warehouseRepository;
        this.acceptanceMapper = acceptanceMapper;
    }

    @Override
    public List<AcceptanceDto> getAll() {
        List<Acceptance> acceptanceList = acceptanceRepository.findAll();
        return acceptanceList.stream().map(acceptanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AcceptanceDto getById(Long id) {
        return acceptanceMapper.toDto(acceptanceRepository.getOne(id));
    }

    @Override
    public AcceptanceDto create(AcceptanceDto dto) {
        Acceptance acceptance = acceptanceMapper.toModel(dto);
        acceptance.setContract(contractRepository.getOne(dto.getContractId()));
        acceptance.setContractor(contractorRepository.getOne(dto.getContractorId()));
        acceptance.setWarehouse(warehouseRepository.getOne(dto.getWarehouseId()));
        acceptance.setCompany(companyRepository.getOne(dto.getCompanyId()));
        acceptance.setProject(projectRepository.getOne(dto.getProjectId()));

        //Что работало в Postman, закомментить следующую строчку
        acceptance.setEmployeeChanged((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return acceptanceMapper.toDto(acceptanceRepository.save(acceptance));
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
                    .map(acceptanceMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return acceptanceRepository.search(searchTerm).stream()
                    .map(acceptanceMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<AcceptanceDto> search(Specification<Acceptance> spec) {
        return executeSearch(acceptanceRepository, acceptanceMapper::toDto, spec);
    }
}
