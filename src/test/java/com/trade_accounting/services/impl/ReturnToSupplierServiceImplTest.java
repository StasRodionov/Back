package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.repositories.ReturnToSupplierRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void getAll_shouldReturnFilledListReturnToSuppliers() {
        when(returnToSupplierRepository.findAll())
                .thenReturn(
                        List.of(ModelStubs.getReturnToSupplier(1L),
                                ModelStubs.getReturnToSupplier(2L),
                                ModelStubs.getReturnToSupplier(3L)));
        List<ReturnToSupplierDto> list = returnToSupplierService.getAll();
        assertEquals(3, list.size());
    }

}
