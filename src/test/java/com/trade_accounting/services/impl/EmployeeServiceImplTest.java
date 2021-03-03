package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Position;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    //Tests
    @Test
    void getAll_shouldReturnListFilledEmployeeDto() {
        when(employeeRepository.getAll())
                .thenReturn(getListEmployeeDtoFromRepo());

        when(departmentRepository.getDepartmentByEmployeeId(anyLong()))
                .thenReturn(getDepartmentDtoFromRepo(1L));

        when(positionRepository.getPositionByEmployeeId(anyLong()))
                .thenReturn(getPositionDtoFromRepo(1L));

        when(imageRepository.getImageByEmployeeId(anyLong()))
                .thenReturn(getImageDtoFromRepo(1L));

        when(roleRepository.getRolesByEmployeeId(anyLong()))
                .thenReturn(getRolesSetFromRepo());

        when(roleRepository.getById(anyLong()))
                .thenReturn(getRoleDtoFromRepo(1L));

        List<EmployeeDto> employees = employeeService.getAll();

        for(EmployeeDto employee : employees) {
            assertTrue(
                    employeeDtoIsFullInited(employee),
                    String.format(
                            "failure - expected that all " +
                            "required fields of employeeDto not null: " +
                            "fail in employeeDto with id %s", employee.getId()
                    )
            );
        }
    }

    @Test
    void search_shouldReturnListFilledEmployeeDto() {
        when(employeeRepository.findAll(Mockito.<Specification<Employee>>any()))
                .thenReturn(getListEmployeeFromRepo());

        List<EmployeeDto> employees = employeeService
                .search(SpecificationStubs.getEmployeeSpecificationStub());

        for(EmployeeDto employee : employees) {
            assertTrue(
                    employeeDtoIsFullInited(employee),
                    String.format(
                            "failure - expected that all " +
                            "required fields of employeeDto not null: " +
                            "fail in employeeDto with id %s", employee.getId()
                    )
            );
        }
    }

    @Test
    void search_shouldReturnEmptyListEmployeeDto() {
        when(employeeRepository.findAll(Mockito.<Specification<Employee>>any()))
                .thenReturn(new ArrayList<>());

        List<EmployeeDto> employees = employeeService
                .search(SpecificationStubs.getEmployeeSpecificationStub());

        assertEquals(0, employees.size(), "failure - expected that size of list of employeeDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledEmployeeDto() {
        when(employeeRepository.getById(anyLong()))
                .thenReturn(getEmployeeDtoFromRepo(1L));

        when(departmentRepository.getDepartmentByEmployeeId(anyLong()))
                .thenReturn(getDepartmentDtoFromRepo(1L));

        when(positionRepository.getPositionByEmployeeId(anyLong()))
                .thenReturn(getPositionDtoFromRepo(1L));

        when(imageRepository.getImageByEmployeeId(anyLong()))
                .thenReturn(getImageDtoFromRepo(1L));

        when(roleRepository.getRolesByEmployeeId(anyLong()))
                .thenReturn(getRolesSetFromRepo());

        when(roleRepository.getById(anyLong()))
                .thenReturn(getRoleDtoFromRepo(1L));

        EmployeeDto employee = employeeService.getById(1L);

        assertTrue(
                employeeDtoIsFullInited(employee),
                "failure - excepted that all required fields of employeeDto not null."
        );
    }

    //TODO add test for getById method if employee not found after adding exception handlers

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        employeeService.create(
                getFullEmployeeDto(1L)
        );

        verify(employeeRepository).existsById(1L);
        verify(roleRepository, times(3)).getOne(anyLong());
        verify(employeeRepository).save(any(Employee.class));
        verify(departmentRepository).getOne(anyLong());
        verify(positionRepository).getOne(anyLong());
        verify(imageRepository).getByImageUrl(anyString());
    }

    //TODO add test for create method if employee already exists after adding exception handlers

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        employeeService.create(
                getFullEmployeeDto(1L)
        );

        verify(employeeRepository).existsById(1L);
        verify(roleRepository, times(3)).getOne(anyLong());
        verify(employeeRepository).save(any(Employee.class));
        verify(departmentRepository).getOne(anyLong());
        verify(positionRepository).getOne(anyLong());
        verify(imageRepository).getByImageUrl(anyString());
    }

    //TODO add test for update method if employee still not exists after adding exception handlers

    @Test
    void deleteById() {
        employeeService.deleteById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void getByEmail() {
        when(employeeRepository.getByEmail(anyString()))
                .thenReturn(getEmployeeDtoFromRepo(1L));

        EmployeeDto employee = employeeService.getByEmail("email@email.ru");

        assertTrue(employeeDtoIsFullInited(employee));
    }


    //Util methods
    EmployeeDto getEmployeeDtoFromRepo(Long id) {
        return new EmployeeDto(id,
                        "LastName",
                        "FirstName",
                        "MiddleName",
                        String.valueOf(id),
                        String.valueOf(id).repeat(11),
                        String.valueOf(id).repeat(12),
                        "Description",
                        "email@email.com",
                        "password");
    }

    EmployeeDto getFullEmployeeDto(Long id) {
        EmployeeDto employee = getEmployeeDtoFromRepo(id);

        employee.setDepartmentDto(getDepartmentDtoFromRepo(id));
        employee.setPositionDto(getPositionDtoFromRepo(id));
        employee.setRoleDto(getRoleDtoSetFromRepo());
        employee.setImageDto(getImageDtoFromRepo(id));

        return employee;
    }

    List<EmployeeDto> getListEmployeeDtoFromRepo() {
        return Stream.of(
                getEmployeeDtoFromRepo(1L),
                getEmployeeDtoFromRepo(2L),
                getEmployeeDtoFromRepo(3L)
        ).collect(Collectors.toList());
    }

    Employee getEmployeeFromRepo(Long id) {
        return new Employee(
                id,
                "LastName",
                "FirstName",
                "MiddleName",
                String.valueOf(id),
                String.valueOf(id).repeat(11),
                String.valueOf(id).repeat(12),
                "Description",
                "email@email.com",
                "password",
                new Department(id, "dep name", String.valueOf(id)),
                new Position(id, "pos name", String.valueOf(id)),
                getRolesSetFromRepo(),
                new Image(id, "img url", String.valueOf(id))
        );
    }

    List<Employee> getListEmployeeFromRepo() {
        return Stream.of(
                getEmployeeFromRepo(1L),
                getEmployeeFromRepo(2L),
                getEmployeeFromRepo(3L)
        ).collect(Collectors.toList());
    }

    DepartmentDto getDepartmentDtoFromRepo(Long id) {
        return new DepartmentDto(id, "Department name", String.valueOf(id));
    }

    PositionDto getPositionDtoFromRepo(Long id) {
        return new PositionDto(id, "Position name", String.valueOf(id));
    }

    ImageDto getImageDtoFromRepo(Long id) {
        return new ImageDto(id, "imageUrl", String.valueOf(id));
    }

    RoleDto getRoleDtoFromRepo(Long id) {
        return new RoleDto(id, "ROLE_USER", String.valueOf(id));
    }

    Set<RoleDto> getRoleDtoSetFromRepo() {
        return Stream.of(
                getRoleDtoFromRepo(1L),
                getRoleDtoFromRepo(2L),
                getRoleDtoFromRepo(3L)
        ).collect(Collectors.toSet());
    }

    Role getRoleFromRepo(Long id) {
        return new Role(id, "ROLE_USER", String.valueOf(id));
    }

    Set<Role> getRolesSetFromRepo() {
        return Stream.of(
                getRoleFromRepo(1L),
                getRoleFromRepo(2L),
                getRoleFromRepo(3L)
        ).collect(Collectors.toSet());
    }

    boolean employeeDtoIsFullInited(EmployeeDto employee) {
        return employee != null
                && employee.getId() != null
                && employee.getLastName() != null
                && employee.getSortNumber() != null
                && employee.getEmail() != null
                && employee.getPassword() != null
                && employee.getRoleDto() != null;
    }
}