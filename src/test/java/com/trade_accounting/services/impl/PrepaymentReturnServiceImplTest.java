package com.trade_accounting.services.impl;

import com.trade_accounting.models.PrepaymentReturn;
import com.trade_accounting.models.dto.PrepaymentReturnDto;
import com.trade_accounting.repositories.PrepaymentReturnRepository;
import com.trade_accounting.services.impl.Stubs.dto.PrepaymentReturnDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.PrepaymentReturnModelStubs;
import com.trade_accounting.utils.mapper.PrepaymentReturnMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PrepaymentReturnServiceImplTest {

    @Mock
    private PrepaymentReturnRepository prepaymentReturnRepository;

    @InjectMocks
    private PrepaymentReturnServiceImpl prepaymentReturnService;

    @Spy
    private PrepaymentReturnMapper prepaymentReturnMapper;

    @Test
    void getAll() {
    }

    @Test
    void getById() {
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
    }

    @Test
    void search() {
    }

    private void saveOrUpdate() {
        when(prepaymentReturnRepository.save(any(PrepaymentReturn.class))).thenReturn(PrepaymentReturnModelStubs.getPrepaymentReturn(1L));
        PrepaymentReturnDto prepaymentReturnDto = prepaymentReturnService.create(PrepaymentReturnDtoStubs.getDto(1L));
        assertEquals(1L,prepaymentReturnDto.getId());
        verify(prepaymentReturnRepository).save(any(PrepaymentReturn.class));
    }
}