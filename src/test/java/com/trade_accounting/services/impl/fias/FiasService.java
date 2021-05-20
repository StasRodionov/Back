package com.trade_accounting.services.impl.fias;

import com.trade_accounting.repositories.fias.AddressDbRepository;
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
public class FiasService {
    @Mock
    private AddressDbRepository addressDbRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private FiasService fiasService;

    @Test
    void getAll_shouldReturnListFilledFiasDto(){

    }

    @Test
    void getAll_shouldReturnEmptyListFiasDto(){

    }

    @Test
    void getById_shouldReturnFilledFiasDto(){

    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate(){

    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate(){

    }

    @Test
    void deleteById(){
        fiasService.deleteById();
        verify(addressDbRepository).deleteById(1);
    }

}
