package com.trade_accounting.services.impl;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.dto.AcceptanceProductionDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.repositories.AcceptanceProductionRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.DtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AcceptanceProductionServiceImplTest {
    @Mock
    AcceptanceProductionRepository acceptanceProductionRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    DtoMapper dtoMapper;

    @InjectMocks
    AcceptanceProductionServiceImpl acceptanceProductionService;

    @Test
    void getAll_shouldReturnListFilledAcceptanceProductionDto() {
        when(acceptanceProductionRepository.findAll())
                .thenReturn(
                        List.of(
                                ModelStubs.getAcceptanceProduction(1L),
                                ModelStubs.getAcceptanceProduction(2L),
                                ModelStubs.getAcceptanceProduction(3L)
                        )
                );

        List<AcceptanceProductionDto> acceptanceProductions = acceptanceProductionService.getAll();

        assertEquals(3, acceptanceProductions.size());
    }

    @Test
    void getById_shouldReturnFilledAcceptanceProduction() {
        when(acceptanceProductionRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getAcceptanceProduction(1L));

        AcceptanceProductionDto acceptanceProductionDto = acceptanceProductionService.getById(1L);

        assertEquals(1, acceptanceProductionDto.getId());
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
        acceptanceProductionService.deleteById(anyLong());
        verify(acceptanceProductionRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(acceptanceProductionRepository.save(any(AcceptanceProduction.class)))
                .thenReturn(ModelStubs.getAcceptanceProduction(1L));

        when(productRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getProduct(1L));

        AcceptanceProductionDto acceptanceProductionDto =
                acceptanceProductionService.create(DtoStubs.getAcceptanceProductionDto(1L));

        assertEquals(1, acceptanceProductionDto.getId());

        verify(acceptanceProductionRepository).save(any(AcceptanceProduction.class));
    }
}
