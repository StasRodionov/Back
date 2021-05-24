package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.services.interfaces.DepartmentService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DtoMapper dtoMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DtoMapper dtoMapper) {
        this.departmentRepository = departmentRepository;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream()
                .map(dtoMapper::departmentToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(Long id) {
        return dtoMapper.departmentToDepartmentDto(
                departmentRepository.findById(id).orElse(new Department())
        );
    }

    @Override
    public DepartmentDto getByName(String name) {
        return dtoMapper.departmentToDepartmentDto(
                departmentRepository.findByName(name).orElse(new Department())
        );
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        Department department = dtoMapper.departmentDtoToDepartment(departmentDto);
        return dtoMapper.departmentToDepartmentDto(
                departmentRepository.save(department)
        );
    }


    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        return create(departmentDto);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
