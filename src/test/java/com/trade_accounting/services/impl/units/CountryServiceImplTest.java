package com.trade_accounting.services.impl.units;

import com.trade_accounting.Stubs.dto.units.CountryDtoStubs;
import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.repositories.units.CountryRepository;
import com.trade_accounting.utils.mapper.units.CountryMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;
    @Spy
    private CountryMapperImpl countryMapper;
    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void getAll_testReturnCountryDtoList() {
        when(countryRepository.getAll()).thenReturn(Stream.of(CountryDtoStubs.getCountryDto(1L)).collect(Collectors.toList()));
        List<CountryDto> countryDtoList = countryService.getAll();

        assertNotNull(countryDtoList, "Failure - expected countryDtoList not null");
        assertEquals(1, countryDtoList.size(), "Failure - expected countryDtoList size is greater than 0");

        for (CountryDto countryDto : countryDtoList) {
            countryDtoIsCorrectlyInited(countryDto);
        }
    }

    @Test
    void getAll_testReturnEmptyCountryDtoList() {
        when(countryRepository.getAll()).thenReturn(new ArrayList<>());

        List<CountryDto> countryDtoList = countryService.getAll();

        assertNotNull(countryDtoList, "Failure - expected countryDtoList not null");
        assertEquals(0, countryDtoList.size(), "Failure - expected countryDtoList size equals 0");
    }

    @Test
    void create_testCountryDtoSuccessfulCreate() {
        CountryDto countryDto = countryService.create(CountryDtoStubs.getCountryDto(1L));

        assertEquals(countryDto, countryService.create(countryDto));
    }

    @Test
    void update_testCountryDtoSuccessfulUpdate() {
        CountryDto countryDto = countryService.update(CountryDtoStubs.getCountryDto(1L));

        assertEquals(countryDto, countryService.create(countryDto));
    }

    @Test
    void getById_testReturnCountryDtoById() {
        CountryDto countryDto = CountryDtoStubs.getCountryDto(1L);

        when(countryRepository.getById(anyLong())).thenReturn(countryDto);

        countryDtoIsCorrectlyInited(countryService.getById(1L));
    }

    @Test
    void deleteById_testCountryDtoSuccessfulDelete() {
        countryService.deleteById(1L);
        verify(countryRepository).deleteById(anyLong());
    }

    private void countryDtoIsCorrectlyInited(CountryDto countryDto) {
        assertNotNull(countryDto, "Failure - countryDto pass failure");
        assertNotNull(countryDto.getId(), "Failure - countryDto 'id' failure");
        assertNotNull(countryDto.getFullName(), "Failure - countryDto 'fullName' failure");
    }

}
