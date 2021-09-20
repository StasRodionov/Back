package com.trade_accounting.services.impl;


import com.trade_accounting.models.IssuedInvoice;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.IssuedInvoiceDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.IssuedInvoiceRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.impl.Stubs.dto.IssuedInvoiceDtoStubs;
import com.trade_accounting.services.impl.Stubs.dto.RetailStoreDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.IssuedInvoiceModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.IssuedInvoiceMapper;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IssuedInvoiceServiceImplTest {

    @Mock
    IssuedInvoiceRepository issuedInvoiceRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    IssuedInvoiceServiceImpl issuedInvoiceService;

    @Spy
    IssuedInvoiceMapper issuedInvoiceMapper;

    @Test
    void getAll() {
        when(issuedInvoiceRepository.findAll()).thenReturn(
                List.of(IssuedInvoiceModelStubs.getIssuedInvoice(1L))
        );

        List<IssuedInvoiceDto> listInv = issuedInvoiceService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById() {
        when(issuedInvoiceRepository.findById(anyLong())).thenReturn(of(IssuedInvoiceModelStubs.getIssuedInvoice(1L)));

        IssuedInvoiceDto issuedInvoiceDto = issuedInvoiceService.getById(1L);
        assertEquals(1, issuedInvoiceDto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        saveOrUpdate();
    }

    @Test
    void delete_update_shouldPassInstructionsSuccessfulDelete() {
        issuedInvoiceRepository.deleteById(anyLong());
        verify(issuedInvoiceRepository).deleteById(anyLong());
    }


    private void saveOrUpdate() {
        when(issuedInvoiceRepository.save(any(IssuedInvoice.class))).thenReturn(IssuedInvoiceModelStubs.getIssuedInvoice(1L));
        IssuedInvoiceDto issuedInvoiceDto = issuedInvoiceService.create(IssuedInvoiceDtoStubs.getDto(1L));
        assertEquals(1,issuedInvoiceDto.getId());
        verify(issuedInvoiceRepository).save(any(IssuedInvoice.class));
    }
}
