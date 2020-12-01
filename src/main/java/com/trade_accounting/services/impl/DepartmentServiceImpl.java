package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.services.interfaces.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.getAll();
    }

    @Override
    public DepartmentDto getById(Long id) {
        return departmentRepository.getById(id);
    }

    @Override
    public void create(DepartmentDto departmentDto) {
        departmentRepository.save(
                new Department(
                    departmentDto.getName(),
                    departmentDto.getSortNumber()
                )
        );
    }

    @Override
    public void update(DepartmentDto departmentDto) {
        departmentRepository.save(
                new Department(
                    departmentDto.getId(),
                    departmentDto.getName(),
                    departmentDto.getSortNumber()
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
