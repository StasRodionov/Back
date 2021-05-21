package com.trade_accounting.services.impl.fias;

import com.trade_accounting.repositories.fias.RegionRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import com.trade_accounting.services.impl.Stubs.DtoStubs;

@ExtendWith(MockitoExtension.class)
public class RegionService {
    @Mock
    private RegionRepository regionRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private RegionServiceImpl regionService;
  /*
    @Test
    void getAll_shouldReturnListFilledRegionDto(){

    }

    @Test
    void getAll_shouldReturnEmptyListRegionDto(){

    }

    @Test
    void getById_shouldReturnFilledRegionDto(){

    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate(){

    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate(){
       // regionService.update(DtoStubs.)

    }

   */

    @Test
    void deleteById(){
        regionService.deleteById(1);
        verify(regionRepository).deleteById(1);
    }

}
