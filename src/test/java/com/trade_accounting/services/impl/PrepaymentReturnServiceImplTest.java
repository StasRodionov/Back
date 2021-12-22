package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.PrepaymentReturn;
import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.dto.PrepaymentReturnDto;
import com.trade_accounting.repositories.PrepaymentReturnRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.PrepaymentReturnDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.PrepaymentReturnModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.utils.mapper.PrepaymentReturnMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        when(prepaymentReturnRepository.findAll()).thenReturn(
                List.of(PrepaymentReturnModelStubs.getPrepaymentReturn(1L))
        );

        List<PrepaymentReturnDto> listPR = prepaymentReturnService.getAll();
        assertNotNull(listPR, "failure - expected that a list of PrepaymentReturnDto not null");
        assertEquals(1, listPR.size(), "failure - expected that a list of PrepaymentReturnDto grater than 0");
    }

    @Test
    void getById() {

        when(prepaymentReturnRepository.getOne(anyLong()))
            .thenReturn(PrepaymentReturnModelStubs.getPrepaymentReturn(1L));

        PrepaymentReturnDto prepaymentReturnDto = prepaymentReturnService.getById(1L);
        System.out.println(prepaymentReturnDto);
        PrepaymentReturnDtoIsCorrectlyInited(prepaymentReturnDto);
        assertNotNull(prepaymentReturnDto, "failure - fail in passed prepaymentReturnDto");
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

    private void PrepaymentReturnDtoIsCorrectlyInited(PrepaymentReturnDto prepaymentReturnDto) {
        assertNotNull(prepaymentReturnDto, "failure - fail in passed prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getId(), "failure - fail in field 'id' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getTime(), "failure - fail in field 'time' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getComment(), "failure - fail in field 'comment' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getCompanyId(), "failure - fail in field 'companyId' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getContractorId(), "failure - fail in field 'contractorId' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getRetailStoreId(), "failure - fail in field 'retailStoreId' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getSumCash(), "failure - fail in field 'sumCash' into prepaymentReturnDto");
        assertNotNull(prepaymentReturnDto.getSumNonСash(), "failure - fail in field 'sumNonСash' into prepaymentReturnDto");
    }
}