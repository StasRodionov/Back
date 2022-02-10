package com.trade_accounting.services.impl;

import com.trade_accounting.models.PrepaymentReturn;
import com.trade_accounting.models.Prepayout;
import com.trade_accounting.models.dto.PrepayoutDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.PrepayoutRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.SpecificationStubs;
import com.trade_accounting.services.impl.Stubs.dto.PrepayoutDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.PrepayoutModelStubs;
import com.trade_accounting.utils.mapper.PrepayoutMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrepayoutServiceImplTest {
    @Mock
    PrepayoutRepository prepayoutRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    RetailStoreRepository retailStoreRepository;

    @Spy
    PrepayoutMapper prepayoutMapper;

    @InjectMocks
    PrepayoutServiceImpl prepayoutService;

    @Test
    void getAll() {
        when(prepayoutRepository.findAll()).thenReturn(
                List.of(PrepayoutModelStubs.getPrepayout(1L))
        );

        List<PrepayoutDto> listPO = prepayoutService.getAll();
        assertNotNull(listPO, "failure - expected that a list of PrepayoutDto not null");
        assertEquals(1, listPO.size(), "failure - expected that a list of PrepayoutDto grater than 0");
    }

    @Test
    void getById() {
        when(prepayoutRepository.getOne(anyLong()))
                .thenReturn(PrepayoutModelStubs.getPrepayout(1L));

        PrepayoutDto prepayoutDto = prepayoutService.getById(1L);
        PrepayoutDtoIsCorrectlyInited(prepayoutDto);
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
        prepayoutRepository.deleteById(anyLong());
        verify(prepayoutRepository).deleteById(anyLong());
    }

    @Test
    void search() {
        when(prepayoutRepository.findAll(Mockito.<Specification<Prepayout>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getPrepayout(1L),
                                ModelStubs.getPrepayout(2L),
                                ModelStubs.getPrepayout(3L)
                        ).collect(Collectors.toList())
                );

        List<PrepayoutDto> prepayoutDtos = prepayoutService
                .search(SpecificationStubs.getPrepayoutSpecificationStub());

        assertNotNull(prepayoutDtos, "failure - expected that a list of PrepayoutDtos not null");
        assertTrue(prepayoutDtos.size() > 0, "failure - expected that a list of PrepayoutDtos greater than 0");

        for (PrepayoutDto prepayoutDto : prepayoutDtos) {
            PrepayoutDtoIsCorrectlyInited(prepayoutDto);
        }
    }

    private void saveOrUpdate() {
        when(prepayoutRepository.save(any(Prepayout.class)))
                .thenReturn(PrepayoutModelStubs.getPrepayout(1L));
        PrepayoutDto prepayoutDto = prepayoutService.create(PrepayoutDtoStubs.getDto(1L));
        assertEquals(1L, prepayoutDto.getId());
        verify(prepayoutRepository).save(any(Prepayout.class));
    }

    private void PrepayoutDtoIsCorrectlyInited(PrepayoutDto prepayoutDto) {
        assertNotNull(prepayoutDto, "failure - fail in passed prepayoutDto");
        assertNotNull(prepayoutDto.getId(), "failure - fail in field 'id' into prepayoutDto");
        assertNotNull(prepayoutDto.getDate(), "failure - fail in field 'date' into prepayoutDto");
        assertNotNull(prepayoutDto.getComment(), "failure - fail in field 'comment' into prepayoutDto");
        assertNotNull(prepayoutDto.getCompanyId(), "failure - fail in field 'companyId' into prepayoutDto");
        assertNotNull(prepayoutDto.getContractorId(), "failure - fail in field 'contractorId' into prepayoutDto");
        assertNotNull(prepayoutDto.getRetailStoreId(), "failure - fail in field 'retailStoreId' into prepayoutDto");
        assertNotNull(prepayoutDto.getCash(), "failure - fail in field 'Cash' into prepayoutDto");
        assertNotNull(prepayoutDto.getCashless(), "failure - fail in field 'Сashless' into prepayoutDto");
        assertNotNull(prepayoutDto.getDiscount(), "failure - fail in field 'Discount' into prepayoutDto");
        assertNotNull(prepayoutDto.getIsSent(), "failure - fail in field 'IsSent' into prepayoutDto");
        assertNotNull(prepayoutDto.getIsPrint(), "failure - fail in field 'IsPrint' into prepayoutDto");
    }
}
