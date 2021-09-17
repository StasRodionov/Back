package com.trade_accounting.services.impl;


import com.trade_accounting.models.BonusProgram;
import com.trade_accounting.models.RetailOperationWithPoints;
import com.trade_accounting.models.dto.BonusProgramDto;
import com.trade_accounting.models.dto.RetailOperationWithPointsDto;
import com.trade_accounting.repositories.BonusProgramRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.FileRepository;
import com.trade_accounting.repositories.RetailOperationWithPointsRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.impl.Stubs.dto.BonusProgramDtoStubs;
import com.trade_accounting.services.impl.Stubs.dto.RetailOperationWithPointsDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.BonusProgramModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailOperationWithPointsModelStubs;
import com.trade_accounting.utils.mapper.BonusProgramMapper;
import com.trade_accounting.utils.mapper.RetailOperationWithPointsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetailOperationWithPointsServiceImplTest {

    @Mock
    private RetailOperationWithPointsRepository retailOperationWithPointsRepository;

    @Mock
    private  BonusProgramRepository bonusProgramRepository;

    @Mock
    private  ContractorRepository contractorRepository;

    @Mock
    private  TaskRepository taskRepository;

    @Mock
    private  FileRepository fileRepository;

    @InjectMocks
    private RetailOperationWithPointsServiceImpl retailOperationWithPointsService;

    @Spy
    private RetailOperationWithPointsMapper retailOperationWithPointsMapper;

    @Test
    void getAll() {

        when(retailOperationWithPointsRepository.findAll()).thenReturn(
                List.of(
                        RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(1L),
                        RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(2L)
                )
        );

        List<RetailOperationWithPointsDto> bonusProgramDtos = retailOperationWithPointsService.getAll();
        assertEquals(2, bonusProgramDtos.size());
    }

    @Test
    void getById() {

        when(retailOperationWithPointsRepository.findById(anyLong()))
                .thenReturn(Optional.of(RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(1L)));

        RetailOperationWithPointsDto retailOperationWithPointsDto = retailOperationWithPointsService.getById(1L);

        assertEquals(1, retailOperationWithPointsDto.getId());
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
        retailOperationWithPointsService.deleteById(anyLong());
        verify(retailOperationWithPointsRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(retailOperationWithPointsRepository.save(any(RetailOperationWithPoints.class)))
                .thenReturn(RetailOperationWithPointsModelStubs.getRetailOperationWithPoints(1L));

        RetailOperationWithPointsDto retailOperationWithPointsDto = retailOperationWithPointsService
                .create(RetailOperationWithPointsDtoStubs.getDto(1L));

        assertEquals(1, retailOperationWithPointsDto.getId());
        verify(retailOperationWithPointsRepository).save(any(RetailOperationWithPoints.class));
    }

}
