package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<EmployeeDto> employeeDtos = employeeRepository.getAll();
        for (EmployeeDto employeeDto : employeeDtos) {
            employeeDto.setDepartmentDto(departmentRepository.getById(employeeDto.getDepartmentDto().getId()));
            employeeDto.setPositionDto(positionRepository.getById(employeeDto.getPositionDto().getId()));
            employeeDto.setImageDto(imageRepository.getById(employeeDto.getImageDto().getId()));
        }

        return employeeDtos;
    }

    @Override
    public EmployeeDto getById(Long id) {
        EmployeeDto employeeDto = employeeRepository.getById(id);
        employeeDto.setDepartmentDto(departmentRepository.getById(employeeDto.getDepartmentDto().getId()));
        employeeDto.setPositionDto(positionRepository.getById(employeeDto.getPositionDto().getId()));
        employeeDto.setImageDto(imageRepository.getById(employeeDto.getImageDto().getId()));
        return employeeDto;
    }

    @Override
    public void create(EmployeeDto employeeDto) {
        Set<Role> roles = new HashSet<>();
        for (RoleDto roleDto : employeeDto.getRoleDto()) {
            roles.add(roleRepository.getOne(roleDto.getId()));
        }

        Employee employee = new Employee(
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
                roles,
                imageRepository.getOne(employeeDto.getImageDto().getId())
        );
        employeeRepository.save(employee);

    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Set<Role> roles = new HashSet<>();
        for (RoleDto roleDto : employeeDto.getRoleDto()) {
            roles.add(roleRepository.getOne(roleDto.getId()));
        }
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
                roles,
                imageRepository.getOne(employeeDto.getImageDto().getId())));

    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);

    }
}
