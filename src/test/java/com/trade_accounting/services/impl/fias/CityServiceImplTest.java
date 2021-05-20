package com.trade_accounting.services.impl.fias;

import com.trade_accounting.repositories.fias.CityRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {
    @Mock
    private CityRepository cityRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void getAll_shouldReturnListFilledCityDto(){

    }

    @Test
    void getAll_shouldReturnEmptyListCityDto(){

    }

    @Test
    void getById_shouldReturnFilledCityDto(){
        
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate(){

    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate(){

    }

    @Test
    void deleteById(){

    }

}
