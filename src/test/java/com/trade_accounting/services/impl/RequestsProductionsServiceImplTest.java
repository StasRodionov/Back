package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.RequestsProductionsDto;
import com.trade_accounting.repositories.RequestsProductionsRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.model.RequestsProductionsModelStubs;
import com.trade_accounting.utils.mapper.RequestsProductionsMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RequestsProductionsServiceImplTest {

    @Mock
    TechnicalCardRepository technicalCardRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Mock
    RequestsProductionsRepository requestsProductionsRepository;

    @Spy
    RequestsProductionsMapper requestsProductionsMapper;

    @InjectMocks
    RequestsProductionsServiceImpl requestsProductionsService;


    @Test
    void getAll() {
        when(requestsProductionsRepository.findAll()).thenReturn(
                        List.of(
                                RequestsProductionsModelStubs.getRequestProductions(1L),
                                RequestsProductionsModelStubs.getRequestProductions(2L),
                                RequestsProductionsModelStubs.getRequestProductions(3L)
                        ));

        List<RequestsProductionsDto> requestsProductionsDtos = requestsProductionsService.getAll();

        assertEquals(3, requestsProductionsDtos.size());
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
    void saveOrUpdate() {
    }

    @Test
    void deleteById() {
    }
}