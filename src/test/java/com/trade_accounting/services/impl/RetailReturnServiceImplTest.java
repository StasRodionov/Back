package com.trade_accounting.services.impl;

import com.trade_accounting.models.RetailReturn;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailReturnDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.RetailReturnRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.impl.Stubs.dto.RetailReturnDtoStubs;
import com.trade_accounting.services.impl.Stubs.dto.RetailStoreDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailReturnModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.RetailReturnMapper;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetailReturnServiceImplTest {

    @Mock
    RetailReturnRepository retailReturnRepository;

    @Mock
    RetailStoreRepository retailStoreRepository;


    @InjectMocks
    RetailReturnServiceImpl retailReturnService;

    @Spy
    RetailReturnMapper retailReturnMapper;

    @Test
    void getAll() {
        when(retailReturnRepository.findAll()).thenReturn(
                List.of(RetailReturnModelStubs.getRetailReturn(1L))
        );

        List<RetailReturnDto> listInv = retailReturnService.getAll();
        assertEquals(1, listInv.size());
    }

    @Test
    void getById() {
        when(retailReturnRepository.findById(anyLong())).thenReturn(java.util.Optional.of(RetailReturnModelStubs.getRetailReturn(1L)));

        RetailReturnDto retailReturnDto = retailReturnService.getById(1L);
        assertEquals(1, retailReturnDto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        saveOrUpdate();
    }

    @Test
    void delete_update_shouldPassInstructionsSuccessfulDelete() {
        retailReturnService.deleteById(anyLong());
        verify(retailReturnRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailReturnRepository.save(any(RetailReturn.class))).thenReturn(RetailReturnModelStubs.getRetailReturn(1L));

        RetailReturnDto retailReturnDto = retailReturnService.create(RetailReturnDtoStubs.getDto(1L));
        assertEquals(1,retailReturnDto.getId());
        verify(retailReturnRepository).save(any(RetailReturn.class));
    }
}
