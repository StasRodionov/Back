package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.repositories.ProductionRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.interfaces.ProductionService;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionServiceImplTest {

    @InjectMocks
    private ProductionServiceImpl productionService;

    @Mock
    private ProductionRepository productionRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @Test
    void getAll_shouldReturnListFilledProductionDto() {
        when(productionService.getAll())
                .thenReturn(
                        Stream.of(
                                DtoStubs.getProductionDto(1L),
                                DtoStubs.getProductionDto(10L),
                                DtoStubs.getProductionDto(20L)
                        )
                                .collect(Collectors.toList())
                );
        List<ProductionDto> productions = productionService.getAll();

        assertNotNull(productions, "Failure - expected that list of productions not null");
        assertEquals(0, productions.size(), "failure - expected that size of list of productions greater than 0");
    }

    @Test
    void getById() {
        //получаем объект либо нулл
        //проверяем запуск метода
    }

    @Test
    void create() {
        //передаем объект без id получаем с ид
    }

    @Test
    void update() {
        //передаем объект, получаем объект с тем же ид
    }

    @Test
    void deleteById() {
        //проверка вызова метода делет
    }
}