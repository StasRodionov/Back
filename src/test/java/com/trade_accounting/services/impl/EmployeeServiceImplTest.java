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
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
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

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledEmployeeDto() {
        when(employeeRepository.findAll())
                .thenReturn(getListEmployeeFromRepo());

        List<EmployeeDto> employees = employeeService.getAll();

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertTrue(employees.size() > 0, "failure - expected that a size of list of employeeDto greater than 0");

        for(EmployeeDto employee : employees) {
            employeeDtoIsCorrectlyInited(employee);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListEmployeeDto() {
        when(employeeRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<EmployeeDto> employees = employeeService.getAll();

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertEquals(0, employees.size(), "failure - expected that size of list of employeeDto equals 0");
    }

    @Test
    void search_shouldReturnListFilledEmployeeDto() {
        when(employeeRepository.findAll(Mockito.<Specification<Employee>>any()))
                .thenReturn(getListEmployeeFromRepo());

        List<EmployeeDto> employees = employeeService
                .search(SpecificationStubs.getEmployeeSpecificationStub());

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertTrue(employees.size() > 0, "failure - expected that a list of employeeDto greater than 0");

        for(EmployeeDto employee : employees) {
            employeeDtoIsCorrectlyInited(employee);
        }
    }

    @Test
    void search_shouldReturnEmptyListEmployeeDto() {
        when(employeeRepository.findAll(Mockito.<Specification<Employee>>any()))
                .thenReturn(new ArrayList<>());

        List<EmployeeDto> employees = employeeService
                .search(SpecificationStubs.getEmployeeSpecificationStub());

        assertNotNull(employees, "failure - expected that a list of employeeDto not null");
        assertEquals(0, employees.size(), "failure - expected that size of list of employeeDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledEmployeeDto() {
        Optional<Employee> employeeFromRepo = Optional.of(getEmployeeFromRepo(1L));

        when(employeeRepository.findById(anyLong()))
                .thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getById(1L);

        assertNotNull(employee, "failure - expected that employee not null.");
        employeeDtoIsCorrectlyInited(employee);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        employeeService.create(
                getFullEmployeeDto(1L)
        );

        verify(roleRepository, times(3)).findById(anyLong());
        verify(employeeRepository).save(any(Employee.class));
        verify(departmentRepository).findById(anyLong());
        verify(positionRepository).findById(anyLong());
        verify(imageRepository).findByImageUrl(anyString());
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        employeeService.update(
                getFullEmployeeDto(1L)
        );

        verify(roleRepository, times(3)).findById(anyLong());
        verify(employeeRepository).save(any(Employee.class));
        verify(departmentRepository).findById(anyLong());
        verify(positionRepository).findById(anyLong());
        verify(imageRepository).findByImageUrl(anyString());
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        employeeService.deleteById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void getByEmail_shouldReturnFilledEmployeeDto() {
        Optional<Employee> employeeFromRepo = Optional.of(getEmployeeFromRepo(1L));
        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getByEmail("email@email.ru");

        assertNotNull(employee, "failure - expected that employee not null.");

        employeeDtoIsCorrectlyInited(employee);
    }

    @Test
    void getByEmail_shouldReturnEmptyEmployeeDto() {
        Optional<Employee> employeeFromRepo = Optional.empty();
        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(employeeFromRepo);

        EmployeeDto employee = employeeService.getByEmail("email@email.ru");

        assertEquals(new EmployeeDto(), employee, "failure - expected that employee was empty");
    }



    void employeeDtoIsCorrectlyInited(EmployeeDto employee) {
        assertNotNull(employee, "Fail in passed employee");
        assertNotNull(employee.getId(), "Fail in field 'id' of employee");
        assertNotNull(employee.getLastName(), "Fail in field 'lastName' of employee");
        assertNotNull(employee.getEmail(), "Fail in field 'email' of employee");
        assertNotNull(employee.getRoleDto(), "Fail in field 'roleDto' of employee");
        assertTrue(employee.getRoleDto().size() > 0, "Expected that size of EmployeeDto role list greater than 0");
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
        return new ImageDto(id, null, String.valueOf(id));
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
}