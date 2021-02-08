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
import java.util.stream.Collectors;

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
            employeeDto.setDepartmentDto(departmentRepository.getDepartmentByEmployeeId(employeeDto.getId()));
            employeeDto.setPositionDto(positionRepository.getPositionByEmployeeId(employeeDto.getId()));
            employeeDto.setImageDto(imageRepository.getImageByEmployeeId(employeeDto.getId()));

            Set<Role> roles = roleRepository.getRolesByEmployeeId(employeeDto.getId());
            employeeDto.setRoleDto(roles != null
                    ? roles.stream().map(role -> roleRepository.getById(role.getId())).collect(Collectors.toSet())
                    : null);
        }

        return employeeDtos;
    }

    @Override
    public EmployeeDto getById(Long id) {
        EmployeeDto employeeDto = employeeRepository.getById(id);
        employeeDto.setDepartmentDto(departmentRepository.getDepartmentByEmployeeId(id));
        employeeDto.setPositionDto(positionRepository.getPositionByEmployeeId(id));
        employeeDto.setImageDto(imageRepository.getImageByEmployeeId(id));

        Set<Role> roles = roleRepository.getRolesByEmployeeId(id);
        employeeDto.setRoleDto(roles != null
                ? roles.stream().map(role -> roleRepository.getById(role.getId())).collect(Collectors.toSet())
                : null);

        return employeeDto;
    }

    @Override
    public void create(EmployeeDto employeeDto) {
        Set<Role> roles = new HashSet<>();
        if (employeeDto.getRoleDto() != null) {
            for (RoleDto roleDto : employeeDto.getRoleDto()) {
                roles.add(roleRepository.getOne(roleDto.getId()));
            }
        }

        employeeRepository.save(new Employee(
                employeeDto.getLastName(),
                employeeDto.getFirstName(),
                employeeDto.getMiddleName(),
                employeeDto.getSortNumber(),
                employeeDto.getPhone(),
                employeeDto.getInn(),
                employeeDto.getDescription(),
                employeeDto.getEmail(),
                employeeDto.getPassword(),
                employeeDto.getDepartmentDto() != null
                        ? departmentRepository.getOne(employeeDto.getDepartmentDto().getId())
                        : null,
                employeeDto.getPositionDto() != null
                        ? positionRepository.getOne(employeeDto.getPositionDto().getId())
                        : null,
                roles,
                employeeDto.getImageDto() != null
                        ? imageRepository.getOne(employeeDto.getImageDto().getId())
                        : null
        ));
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Set<Role> roles = new HashSet<>();
        if (employeeDto.getRoleDto() != null) {
            for (RoleDto roleDto : employeeDto.getRoleDto()) {
                roles.add(roleRepository.getOne(roleDto.getId()));
            }
        }

        employeeRepository.save(new Employee(
                employeeDto.getId(),
                employeeDto.getLastName(),
                employeeDto.getFirstName(),
                employeeDto.getMiddleName(),
                employeeDto.getSortNumber(),
                employeeDto.getPhone(),
                employeeDto.getInn(),
                employeeDto.getDescription(),
                employeeDto.getEmail(),
                employeeDto.getPassword(),
                employeeDto.getDepartmentDto() != null
                        ? departmentRepository.getOne(employeeDto.getDepartmentDto().getId())
                        : null,
                employeeDto.getPositionDto() != null
                        ? positionRepository.getOne(employeeDto.getPositionDto().getId())
                        : null,
                roles,
                employeeDto.getImageDto() != null
                        ? imageRepository.getOne(employeeDto.getImageDto().getId())
                        : null
        ));

    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public EmployeeDto getByEmail(String email) {
        return employeeRepository.getByEmail(email);
    }

}
