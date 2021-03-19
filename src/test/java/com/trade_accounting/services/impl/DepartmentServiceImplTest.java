package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void getAll_shouldReturnListFilledDepartmentDto() {
        when(departmentRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getDepartment(1L),
                                ModelStubs.getDepartment(2L),
                                ModelStubs.getDepartment(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<DepartmentDto> departments = departmentService.getAll();

        assertNotNull(departments, "Failure - expected that list of department not null");
        assertTrue(departments.size() > 0, "failure - expected that size of list of department greater than 0");

        for(DepartmentDto departmentDto : departments){
            departmentDtoIsCorrectlyInited(departmentDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListDepartmentDto() {
        when(departmentRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<DepartmentDto> departments = departmentService.getAll();

        assertNotNull(departments, "Failure - expected that list of department not null");
        assertEquals(0, departments.size(), "failure - expected that size of list of department equals 0");
    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    void departmentDtoIsCorrectlyInited(DepartmentDto departmentDto) {
        assertNotNull(departmentDto, "failure - fail in passed departmentDto");
        assertNotNull(departmentDto.getId(), "failure - fail in field 'id' into departmentDto");
        assertNotNull(departmentDto.getName(), "failure - fail in field 'name' into departmentDto");
        assertNotNull(departmentDto.getSortNumber(), "failure - fail in field 'sortNumber' into departmentDto");
    }
}