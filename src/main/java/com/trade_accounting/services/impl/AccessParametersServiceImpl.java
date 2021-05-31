package com.trade_accounting.services.impl;

import com.trade_accounting.models.AccessParameters;
import com.trade_accounting.models.dto.AccessParametersDto;
import com.trade_accounting.repositories.AccessParametersRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.services.interfaces.AccessParametersService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccessParametersServiceImpl implements AccessParametersService {

    private final AccessParametersRepository accessParametersRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    private final DtoMapper dtoMapper;

    public AccessParametersServiceImpl(AccessParametersRepository accessParametersRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, DtoMapper dtoMapper) {
        this.accessParametersRepository = accessParametersRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public List<AccessParametersDto> getAll() {
        return accessParametersRepository.findAll().stream()
                .map(dtoMapper::AccessParametersToAccessParametersDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccessParametersDto getById(Long id) {
        return dtoMapper.AccessParametersToAccessParametersDto(accessParametersRepository.findById(id).orElse(new AccessParameters()));
    }

    @Override
    public AccessParametersDto create(AccessParametersDto dto) {
        return dtoMapper.AccessParametersToAccessParametersDto(accessParametersRepository
                .save(AccessParameters.builder().id(dto.getId()).generalAccess(dto.getGeneralAccess())
                        .employee(dtoMapper.employeeDtoToEmployee(employeeRepository.getById(dto.getEmployeeId())))
                        .department(dtoMapper.departmentDtoToDepartment(departmentRepository.getById(dto.getDepartmentId()))).build()));
    }

    @Override
    public AccessParametersDto update(AccessParametersDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        accessParametersRepository.deleteById(id);
    }
}
