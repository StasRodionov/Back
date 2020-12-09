package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final RoleRepository roleRepository;
    private final ImageRepository imageRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               PositionRepository positionRepository,
                               RoleRepository roleRepository,
                               ImageRepository imageRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public EmployeeDto getById(Long id) {
        return employeeRepository.getById(id);
    }

    @Override
    public void create(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                null,
                employeeDto.getLastName(),
                employeeDto.getFirstName(),
                employeeDto.getMiddleName(),
                employeeDto.getSortNumber(),
                employeeDto.getPhone(),
                employeeDto.getInn(),
                employeeDto.getDescription(),
                employeeDto.getEmail(),
                employeeDto.getPassword(),
                departmentRepository.getOne(employeeDto.getDepartmentDto().getId()),
                positionRepository.getOne(employeeDto.getPositionDto().getId()),
                roleRepository.getOne(employeeDto.getRoleDto().getId()),
                imageRepository.getOne(employeeDto.getImageDto().getId())
        );
        employeeRepository.save(employee);

    }

    @Override
    public void update(EmployeeDto employeeDto) {
        employeeRepository.save(new Employee(employeeDto.getId(),
                employeeDto.getLastName(),
                employeeDto.getFirstName(),
                employeeDto.getMiddleName(),
                employeeDto.getSortNumber(),
                employeeDto.getPhone(),
                employeeDto.getInn(),
                employeeDto.getDescription(),
                employeeDto.getEmail(),
                employeeDto.getPassword(),
                departmentRepository.getOne(employeeDto.getDepartmentDto().getId()),
                positionRepository.getOne(employeeDto.getPositionDto().getId()),
                roleRepository.getOne(employeeDto.getRoleDto().getId()),
                imageRepository.getOne(employeeDto.getImageDto().getId())));

    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);

    }
}
