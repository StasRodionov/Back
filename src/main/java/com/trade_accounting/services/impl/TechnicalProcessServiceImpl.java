package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalProcess;
import com.trade_accounting.models.dto.TechnicalProcessDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.StagesProductionRepository;
import com.trade_accounting.repositories.TechnicalProcessRepository;
import com.trade_accounting.services.interfaces.TechnicalProcessService;
import com.trade_accounting.utils.mapper.TechnicalProcessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalProcessServiceImpl implements TechnicalProcessService {

    private TechnicalProcessRepository technicalProcessRepository;
    private TechnicalProcessMapper technicalProcessMapper;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private StagesProductionRepository stagesProductionRepository;


    @Override
    public List<TechnicalProcessDto> getAll() {
        return technicalProcessRepository.findAll().stream()
                .map(technicalProcessMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalProcessDto getById(Long id) {
        return technicalProcessMapper.toDto(technicalProcessRepository.getOne(id));
    }

    @Override
    public TechnicalProcessDto create(TechnicalProcessDto dto) {
        TechnicalProcess technicalProcess = technicalProcessMapper.toModel(dto);
        technicalProcess.setStagesProductionSet(
                stagesProductionRepository.getStagesProductionsByIdIn(dto.getStagesProductionIds()));
        technicalProcess.setDepartmentOwner(departmentRepository.getOne(dto.getDepartmentOwnerId()));
        technicalProcess.setEmployeeOwner(employeeRepository.getOne(dto.getEmployeeOwnerId()));
        technicalProcess.setEmployeeWhoLastChanged(employeeRepository.getOne(dto.getEmployeeWhoLastChangedId()));
        return technicalProcessMapper.toDto(technicalProcessRepository.save(technicalProcess));
    }

    @Override
    public TechnicalProcessDto update(TechnicalProcessDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalProcessRepository.deleteById(id);
    }

    @Override
    public List<TechnicalProcessDto> search(String request) {
        return technicalProcessRepository.search(request).stream()
                .map(technicalProcessMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TechnicalProcessDto getByName(String name) {
        return technicalProcessMapper.toDto(technicalProcessRepository.getByName(name).orElse(null));
    }
}
