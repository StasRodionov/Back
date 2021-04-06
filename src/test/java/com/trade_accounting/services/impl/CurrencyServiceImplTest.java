package com.trade_accounting.services.impl;

import com.trade_accounting.models.Currency;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.SpecificationStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

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
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void getAll_shouldReturnListFilledCurrencyDto() {
        when(currencyRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getCurrency(1L),
                                ModelStubs.getCurrency(2L),
                                ModelStubs.getCurrency(3L)
                        ).collect(Collectors.toList())
                );
        List<CurrencyDto> currencyDtoList = currencyService.getAll();
        assertNotNull(
                currencyDtoList,
                "failure - expected that a list of currencyDto not null"
        );
        assertTrue(
                currencyDtoList.size() > 0,
                "failure - expected that a list of currencyDto grater than 0"
        );
        for (CurrencyDto currencyDto : currencyDtoList) {
            currencyDtoIsCorrectlyInited(currencyDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListCurrencyDto() {
        when(currencyRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<CurrencyDto> currencys = currencyService.getAll();

        assertNotNull(currencys, "Failure - expected that list of currency not null");
        assertEquals(0, currencys.size(), "failure - expected that size of list of currency equals 0");
    }

    @Test
    void search_shouldReturnListFilledCurrencyDto() {
        when(currencyRepository.findAll(Mockito.<Specification<Currency>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getCurrency(1L),
                                ModelStubs.getCurrency(2L),
                                ModelStubs.getCurrency(3L)
                        ).collect(Collectors.toList())
                );

        List<CurrencyDto> currencys = currencyService
                .search(SpecificationStubs.getCurrencySpecificationStub());

        assertNotNull(currencys, "failure - expected that a list of currencyDto not null");
        assertTrue(currencys.size() > 0, "failure - expected that a list of currencyDto greater than 0");

        for(CurrencyDto currency : currencys) {
            currencyDtoIsCorrectlyInited(currency);
        }
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        currencyService.create(
                DtoStubs.getCurrencyDto(1L)
        );

        verify(currencyRepository).save(any(Currency.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        currencyService.update(
                DtoStubs.getCurrencyDto(1L)
        );

        verify(currencyRepository).save(any(Currency.class));
    }

    @Test
    void getById_shouldReturnFilledCurrencyDto() {
        Optional<Currency> currencyFromRepo =
                Optional.of(ModelStubs.getCurrency(1L));

        when(currencyRepository.findById(anyLong()))
                .thenReturn(currencyFromRepo);

        CurrencyDto currencyDto = currencyService.getById(1L);

        currencyDtoIsCorrectlyInited(currencyDto);
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        currencyService.deleteById(1L);
        verify(currencyRepository).deleteById(anyLong());
    }

    private void currencyDtoIsCorrectlyInited(CurrencyDto currencyDto) {
        assertNotNull(currencyDto, "failure - fail in passed currencyDto");
        assertNotNull(currencyDto.getId(), "failure - fail in field 'id' into currencyDto");
        assertNotNull(currencyDto.getFullName(), "failure - fail in field 'fullName' into currencyDto");
    }
}
