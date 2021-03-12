package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.EmployeeService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final ImageRepository imageRepository;
    private final RoleRepository roleRepository;

    private final DtoMapper dtoMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DtoMapper dtoMapper) {
        this.employeeRepository = employeeRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(dtoMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> search(Specification<Employee> specification) {
        return employeeRepository.findAll(specification).stream()
                .map(dtoMapper::employeeToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        return dtoMapper.employeeToEmployeeDto(emp.orElse(new Employee()));
    }

    @Override
    public void create(EmployeeDto employeeDto) {
        Employee employee = dtoMapper.employeeDtoToEmployee(employeeDto);

        Position position;
        Image image;
        Set<Role> roles;

        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Employee employee = dtoMapper.employeeDtoToEmployee(employeeDto);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);

        if(employee.isEmpty()) {
            return new EmployeeDto();
        }

        return dtoMapper.employeeToEmployeeDto(employee.get());
    }
}
