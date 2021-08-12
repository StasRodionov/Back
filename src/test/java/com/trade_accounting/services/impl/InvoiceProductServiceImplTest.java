package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.repositories.InvoiceProductRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceProductServiceImplTest {
    @Mock
    private InvoiceProductRepository invoiceProductRepository;

    @InjectMocks
    private InvoiceProductServiceImpl invoiceProductService;

    @Spy
    private DtoMapperImpl dtoMapper;

    @Test
    void getAll_shouldReturnListFilledInvoiceProductDto() {
        when(invoiceProductRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getInvoiceProduct(1L),
                                ModelStubs.getInvoiceProduct(2L),
                                ModelStubs.getInvoiceProduct(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceProductDto> invoiceProductDtoList = invoiceProductService.getAll();
        assertNotNull(
                invoiceProductDtoList,
                "failure - expected that a list of invoiceProductDto not null"
        );
        assertTrue(
                invoiceProductDtoList.size() > 0,
                "failure - expected that a list of invoiceProductDto grater than 0"
        );
        for (InvoiceProductDto invoiceProductDto : invoiceProductDtoList) {
            invoiceProductListDtoIsCorrectlyInited(invoiceProductDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceProductDtoList() {
        when(invoiceProductRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<InvoiceProductDto> invoiceDtoList =
                invoiceProductService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceProductDto not null"
        );
        assertEquals(
                0, invoiceDtoList.size(),
                "failure - expected that size of list of invoiceProductDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledInvoiceProductDto() {
        Optional<InvoiceProduct> invoiceProductDtoFromRepo =
                Optional.of(
                        ModelStubs.getInvoiceProduct(1L)
                );

        when(invoiceProductRepository.findById(anyLong()))
                .thenReturn(invoiceProductDtoFromRepo);

        InvoiceProductDto invoiceProductDto =
                invoiceProductService.getById(1L);

        assertNotNull(
                invoiceProductDto,
                "failure - expected that invoiceDto not null"
        );

        invoiceProductListDtoIsCorrectlyInited(invoiceProductDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceProductService.create(
                DtoStubs.getInvoiceProductDto(1L)
        );

        verify(invoiceProductRepository).save(any(InvoiceProduct.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceProductService.update(
                DtoStubs.getInvoiceProductDto(1L)
        );
        verify(invoiceProductRepository).save(any(InvoiceProduct.class));
    }

    @Test
    void deleteById() {
        invoiceProductService.deleteById(1L);
        verify(invoiceProductRepository).deleteById(1L);
    }

    void invoiceProductListDtoIsCorrectlyInited(InvoiceProductDto invoiceProductDto) {
        assertNotNull(invoiceProductDto, "Fail in passed invoiceDto");
        assertNotNull(invoiceProductDto.getId(), "Fail in field 'id' of invoiceDto");
    }
}
