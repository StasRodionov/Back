package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.repositories.ReturnToSupplierRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnToSupplierServiceImplTest {

    @Mock
    ReturnToSupplierRepository returnToSupplierRepository;

    @Spy
    DtoMapperImpl dtoMapper;

    @InjectMocks
    ReturnToSupplierServiceImpl returnToSupplierService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(returnToSupplierRepository);
    }

    @Test
    void getAll_shouldReturnFilledListReturnToSuppliers() {
        when(returnToSupplierRepository.getAll())
                .thenReturn(
                        List.of(DtoStubs.getReturnToSupplierDto(1L),
                                DtoStubs.getReturnToSupplierDto(2L),
                                DtoStubs.getReturnToSupplierDto(3L)));
        List<ReturnToSupplierDto> list = returnToSupplierService.getAll();
        assertEquals(3, list.size());
    }

}
