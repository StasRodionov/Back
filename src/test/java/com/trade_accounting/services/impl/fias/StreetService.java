package com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.models.fias.Street;
import com.trade_accounting.repositories.fias.StreetRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class StreetService {
    @Mock
    private StreetRepository streetRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private StreetServiceImpl streetService;
/*
    @Test
    void getAll_shouldReturnListFilledStreetDto() {

    }

    @Test
    void getAll_shouldReturnEmptyListStreetDto() {

    }

    @Test
    void getById_shouldReturnFilledStreetDto() {

    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {

    }

 */
    @Test
   public void update_shouldPassInstructionsSuccessfulUpdate() {
       // streetService.update(DtoStubs.getStreetDto(1L));
        verify(streetRepository).save(any(Street.class));

    }

    @Test
   public void deleteById() {
        streetService.deleteById(1);
        verify(streetRepository).deleteById(1);
    }

}
