package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.repositories.SupplierAccountRepository;
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
class SupplierAccountServiceImplTest {

    @Spy
    private DtoMapperImpl mapper;

    @Mock
    private SupplierAccountRepository repository;

    @InjectMocks
    private SupplierAccountServiceImpl service;

    @Test
    void getRepositoryFilledTest() {
        assertNotNull(repository, "Repository is not filled");
    }

    @Test
    void getAll_shouldReturnListFilledSupplierAccountDto() {
        when(repository.getAll())
                .thenReturn(
                        List.of(DtoStubs.getSupplierAccountDto(1L),
                                DtoStubs.getSupplierAccountDto(2L),
                                DtoStubs.getSupplierAccountDto(3L)));
        List<SupplierAccountDto> list = service.getAll();
        assertNotNull(list, "list is not filled");
        assertEquals(3, list.size());
    }
}