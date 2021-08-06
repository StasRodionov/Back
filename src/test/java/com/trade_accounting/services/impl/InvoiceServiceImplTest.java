package com.trade_accounting.services.impl;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.InvoiceDtoStubs;
import com.trade_accounting.utils.mapper.CompanyMapper;
import com.trade_accounting.utils.mapper.ContractorMapper;
import com.trade_accounting.utils.mapper.InvoiceMapper;
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
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @Mock
    private  CompanyRepository companyRepository;
    @Mock
    private ContractorRepository contractorRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private  ContractorMapper contractorMapper;
    @Mock
    private  CompanyMapper companyMapper;

    @Spy
    private InvoiceMapper invoiceMapper;

    @Test
    void getAll_shouldReturnListFilledInvoiceDto() {
        when(invoiceRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getInvoice(1L),
                                ModelStubs.getInvoice(2L),
                                ModelStubs.getInvoice(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceDto> invoiceDtoList = invoiceService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );
        assertTrue(
                invoiceDtoList.size() > 0,
                "failure - expected that a list of invoiceDto grater than 0"
        );
        for (InvoiceDto invoiceDto : invoiceDtoList) {
            invoiceListDtoIsCorrectlyInited(invoiceDto);
        }
    }

    @Test
    void getAll_shouldReturnListFilledInvoiceDtoByType() { //этот тест не проходит вообще
        when(invoiceRepository.findByTypeOfInvoice(any(TypeOfInvoice.class)))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getInvoice(1L),
                                ModelStubs.getInvoice(2L),
                                ModelStubs.getInvoice(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceDto> invoiceDtoList = invoiceService.getAll("RECEIPT");
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );


        assertTrue(
                invoiceDtoList.size() > 0,
                "failure - expected that a list of invoiceDto grater than 0"
        );


        verify(invoiceRepository).findByTypeOfInvoice(any(TypeOfInvoice.class));
        for (InvoiceDto invoiceDto : invoiceDtoList) {
            invoiceListDtoIsCorrectlyInited(invoiceDto);
        }


    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceDtoList() {
        when(invoiceRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<InvoiceDto> invoiceDtoList =
                invoiceService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );
        assertEquals(
                0, invoiceDtoList.size(),
                "failure - expected that size of list of invoiceDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledInvoiceDto() {
        Optional<Invoice> invoiceDtoFromRepo =
                Optional.of(
                        ModelStubs.getInvoice(1L)
                );

        when(invoiceRepository.findById(anyLong()))
                .thenReturn(invoiceDtoFromRepo);

        InvoiceDto invoiceDto =
                invoiceService.getById(1L);

        assertNotNull(
                invoiceDto,
                "failure - expected that invoiceDto not null"
        );

        invoiceListDtoIsCorrectlyInited(invoiceDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceService.create(
                InvoiceDtoStubs.getInvoiceDto(1L)
        );

        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceService.update(
                InvoiceDtoStubs.getInvoiceDto(1L)
        );

        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void deleteById() {
        invoiceService.deleteById(1L);
        verify(invoiceRepository).deleteById(1L);
    }


    void invoiceListDtoIsCorrectlyInited(InvoiceDto invoiceDto) {
        assertNotNull(invoiceDto, "Fail in passed invoiceDto");
        assertNotNull(invoiceDto.getId(), "Fail in field 'id' of invoiceDto");
    }

}
