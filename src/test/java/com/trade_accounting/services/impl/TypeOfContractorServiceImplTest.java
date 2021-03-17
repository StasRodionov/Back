package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TypeOfContractorServiceImplTest {

    @Spy
    private DtoMapperImpl dtoMapper;

    @Mock
    private TypeOfContractorRepository typeOfContractorRepository;

    @InjectMocks
    private TypeOfContractorServiceImpl typeOfContractorService;

    @Test
    void getAll_shouldReturnListFilledTypeOfContactorDto() {
        when(typeOfContractorRepository.findAll())
                .thenReturn(Stream.of(
                ModelStubs.getTypeOfContractor(1L),
                ModelStubs.getTypeOfContractor(2L),
                ModelStubs.getTypeOfContractor(3L)
        ).collect(Collectors.toList()));

        List<TypeOfContractorDto> typesOfContactors = typeOfContractorService.getAll();

        assertNotNull(typesOfContactors, "failure - expected that a list of typeOfContactorsDto not null");
        assertTrue(typesOfContactors.size() > 0, "failure - expected that a size of list of typeOfContactorsDto greater than 0");

    }

    @Test
    void getById() {
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

    @Test
    void getByName() {
    }
}