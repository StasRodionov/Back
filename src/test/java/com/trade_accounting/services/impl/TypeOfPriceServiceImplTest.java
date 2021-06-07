package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.DtoMapperImpl;
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
class TypeOfPriceServiceImplTest {

    @Mock
    private TypeOfPriceRepository typeOfPriceRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private TypeOfPriceServiceImpl typeOfPriceService;

    @Test
    void getAll_shouldReturnListFilledTypeOfPriceDto() {
        when(typeOfPriceRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getTypeOfPrice(1L),
                                ModelStubs.getTypeOfPrice(2L),
                                ModelStubs.getTypeOfPrice(3L)
                        ).collect(Collectors.toList())
                );

        List<TypeOfPriceDto> typeOfPrices = typeOfPriceService.getAll();

        assertNotNull(typeOfPrices, "failure - expected that a list of typeOfPriceDto not null");
        assertTrue(typeOfPrices.size() > 0, "failure - expected that a list of typeOfPriceDto grater than 0");

        for (TypeOfPriceDto typeOfPrice : typeOfPrices) {
            typeOfPriceDtoIsCorrectlyInited(typeOfPrice);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListTypeOfPriceDto() {
        when(typeOfPriceRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<TypeOfPriceDto> typeOfPrices = typeOfPriceService.getAll();

        assertNotNull(typeOfPrices, "failure - expected that a list of typeOfPriceDto not null");
        assertEquals(0, typeOfPrices.size(), "failure - expected that size of list of typeOfPriceDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledTypeOfPriceDto() {
        Optional<TypeOfPrice> typeOfPriceFromRepo =
                Optional.of(ModelStubs.getTypeOfPrice(1L));

        when(typeOfPriceRepository.findById(anyLong()))
                .thenReturn(typeOfPriceFromRepo);

        TypeOfPriceDto typeOfPrice = typeOfPriceService.getById(1L);

        assertNotNull(typeOfPrice, "failure - expected that typeOfPrice not null");
        typeOfPriceDtoIsCorrectlyInited(typeOfPrice);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        typeOfPriceService.create(
                DtoStubs.getTypeOfPriceDto(1L)
        );

        verify(typeOfPriceRepository).save(any(TypeOfPrice.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        typeOfPriceService.update(
                DtoStubs.getTypeOfPriceDto(1L)
        );

        verify(typeOfPriceRepository).save(any(TypeOfPrice.class));
    }

    @Test
    void deleteById() {
        typeOfPriceService.deleteById(1L);
        verify(typeOfPriceRepository).deleteById(1L);
    }

    void typeOfPriceDtoIsCorrectlyInited(TypeOfPriceDto typeOfPrice) {
        assertNotNull(typeOfPrice, "Fail in passed employee");
        assertNotNull(typeOfPrice.getId(), "Fail in field 'id' of typeOfPrice");
    }
}