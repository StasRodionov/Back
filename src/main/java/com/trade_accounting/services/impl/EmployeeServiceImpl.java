package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.EmployeeService;
import com.trade_accounting.utils.DtoMapper;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
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
    private final RoleRepository roleRepository;
    private final ImageRepository imageRepository;

    private final DtoMapper dtoMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PositionRepository positionRepository,
                               DepartmentRepository departmentRepository,
                               RoleRepository roleRepository,
                               ImageRepository imageRepository, DtoMapper dtoMapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
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

        DepartmentDto department = employeeDto.getDepartmentDto();
        PositionDto position = employeeDto.getPositionDto();
        ImageDto imageDto = employeeDto.getImageDto();
        Set<RoleDto> setOfRoleDto = employeeDto.getRoleDto();

        if(department != null) {
            employee.setDepartment(
                    departmentRepository.findById(department.getId()).orElse(null)
            );
        }

        if(position != null) {
            employee.setPosition(
                    positionRepository.findById(position.getId()).orElse(null)
            );
        }

        if(imageDto != null) {
            employee.setImage(
                    imageRepository.getOne(imageDto.getId())
            );
        }

        if(setOfRoleDto != null) {
            Set<Role> roles = setOfRoleDto.stream()
                    .map(role ->
                            role != null
                                    ? roleRepository.findById(role.getId()).orElse(null)
                                    : null)
                    .collect(Collectors.toSet());

            employee.setRoles(roles);
        }


        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        create(employeeDto);
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        Optional<ImageDto> optional = Optional.ofNullable(imageRepository.getImageByEmployeeId(id));
        if (optional.isPresent()) {
            //Files.deleteIfExists(Paths.get(optional.get().getImageUrl()));
            imageRepository.deleteById(optional.get().getId());
        }
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
