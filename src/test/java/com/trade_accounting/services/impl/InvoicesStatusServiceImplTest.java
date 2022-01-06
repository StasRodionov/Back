package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoicesStatus;
import com.trade_accounting.models.dto.InvoicesStatusDto;
import com.trade_accounting.repositories.InvoicesStatusRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.InvoicesStatusDtoStubs;
import com.trade_accounting.utils.mapper.InvoicesStatusMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class InvoicesStatusServiceImplTest {

    @InjectMocks
    private InvoicesStatusServiceImpl invoicesStatusService;
    @Mock
    private InvoicesStatusRepository invoicesStatusRepository;
    @Spy
    private InvoicesStatusMapper invoicesStatusMapper;
    @Test
    void getAll() {
        when(invoicesStatusRepository.findAll()).
                thenReturn(
                Stream.of(
                        ModelStubs.getInvoicesStatus(1L),
                        ModelStubs.getInvoicesStatus(2L),
                        ModelStubs.getInvoicesStatus(3L)
                ).collect(Collectors.toList()));

        List<InvoicesStatusDto> invoicesStatusDtos = invoicesStatusService.getAll();
        assertEquals(3, invoicesStatusDtos.size());
    }


    @Test
    void getById() {
        when(invoicesStatusRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getInvoicesStatus(1L));

        InvoicesStatusDto invoicesStatusDto = invoicesStatusService.getById(1L);

        invoicesStatusIsCorrectlyInited(invoicesStatusDto);
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        invoicesStatusRepository.deleteById(anyLong());
        verify(invoicesStatusRepository).deleteById(anyLong());
    }

    @Test
    void getByName() {
        Optional<InvoicesStatus> invoicesStatusOptional = Optional.of(ModelStubs.getInvoicesStatus(1L));

        when(invoicesStatusRepository.findByStatusName(anyString()))
                .thenReturn(invoicesStatusOptional);

        InvoicesStatusDto invoicesStatusDto = invoicesStatusService.getByName("новый");

        assertNotNull(invoicesStatusDto, "failure - expected that invoicesStatusDto not null.");
        invoicesStatusIsCorrectlyInited(invoicesStatusDto);
    }

    void  invoicesStatusIsCorrectlyInited(InvoicesStatusDto invoicesStatusDto) {
        assertNotNull(invoicesStatusDto, "Fail in passed invoicesStatus");
        assertNotNull(invoicesStatusDto.getId(), "Fail in field 'id' of invoicesStatus");
        assertNotNull(invoicesStatusDto.getStatusName(), "Fail in field 'status' of invoicesStatus");
    }

    private void saveOrUpdate() {
        when(invoicesStatusRepository.save(any(InvoicesStatus.class))).thenReturn(ModelStubs.getInvoicesStatus(1L));
        InvoicesStatusDto invoicesStatusDto = invoicesStatusService.create(InvoicesStatusDtoStubs.getInvoicesStatusDto(1L));
        assertEquals(1,invoicesStatusDto.getId());
        verify(invoicesStatusRepository).save(any(InvoicesStatus.class));
    }
}