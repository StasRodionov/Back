package com.trade_accounting.services.impl;

import com.trade_accounting.models.BalanceAdjustment;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import com.trade_accounting.repositories.BalanceAdjustmentRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.BalanceAdjustmentDtoStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceAdjustmentServiceImplTest {

    @Mock
    BalanceAdjustmentRepository balanceAdjustmentRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Spy
    DtoMapperImpl dtoMapper;

    @InjectMocks
    BalanceAdjustmentServiceImpl balanceAdjustmentService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(balanceAdjustmentRepository);
    }

    @Test
    void getAll_shouldReturnFilledListBalanceAdjustments() {
        when(balanceAdjustmentRepository.getAll())
                .thenReturn(
                        List.of(BalanceAdjustmentDtoStubs.getBalanceAdjustmentDto(1L),
                                BalanceAdjustmentDtoStubs.getBalanceAdjustmentDto(2L),
                                BalanceAdjustmentDtoStubs.getBalanceAdjustmentDto(3L)));
        List<BalanceAdjustmentDto> list = balanceAdjustmentService.getAll();
        assertEquals(3, list.size());
    }

    @Test
    void getById_shouldReturnFilledBalanceAdjustment() {
        Optional<BalanceAdjustment> model = Optional.of(ModelStubs.getBalanceAdjustment(1L));
        when(balanceAdjustmentRepository.findById(anyLong())).thenReturn(model);
        BalanceAdjustmentDto dto = balanceAdjustmentService.getById(1L);
        assertEquals(1L, dto.getId());
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
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        balanceAdjustmentService.deleteById(anyLong());
        verify(balanceAdjustmentRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(balanceAdjustmentRepository.save(any())).thenReturn(ModelStubs.getBalanceAdjustment(1L));
        BalanceAdjustmentDto dto = balanceAdjustmentService.create(BalanceAdjustmentDtoStubs.getBalanceAdjustmentDto(1L));
        assertEquals(1, dto.getId());
        verify(balanceAdjustmentRepository).save(any(BalanceAdjustment.class));
    }
}
