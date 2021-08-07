package com.trade_accounting.services.impl;

import com.trade_accounting.models.Loss;
import com.trade_accounting.models.dto.LossDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LossProductRepository;
import com.trade_accounting.repositories.LossRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.impl.Stubs.dto.LossDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.LossModelStubs;
import com.trade_accounting.utils.mapper.LossMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LossServiceImplTest {
    @InjectMocks
    private LossServiceImpl lossService;

    @Mock
    private LossRepository lossRepository;

    @Mock
    private LossProductRepository lossProductRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Spy
    private LossMapper dtoMapper;

    @Test
    void getAll() {
        when(lossRepository.findAll())
                .thenReturn(
                        List.of(
                                LossModelStubs.getLoss(1L),
                                LossModelStubs.getLoss(2L),
                                LossModelStubs.getLoss(3L)
                        ));

        List<LossDto> lossDtosDtos = lossService.getAll();

        assertEquals(3, lossDtosDtos.size());
    }

    @Test
    void getById() {
        when(lossRepository.getOne(anyLong()))
                .thenReturn(LossModelStubs.getLoss(1L));

        LossDto lossDtos = lossService.getById(1L);

        assertEquals(1, lossDtos.getId());
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
        lossService.deleteById(anyLong());
        verify(lossRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(lossRepository.save(any(Loss.class)))
                .thenReturn(LossModelStubs.getLoss(1L));

        LossDto lossDto = lossService
                .create(LossDtoStubs.getDto(1L));

        assertEquals(1, lossDto.getId());
        verify(lossRepository).save(any(Loss.class));
    }
}