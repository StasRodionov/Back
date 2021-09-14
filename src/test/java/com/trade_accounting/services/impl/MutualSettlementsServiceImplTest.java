package com.trade_accounting.services.impl;

import com.trade_accounting.models.MutualSettlements;
import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.dto.MutualSettlementsDto;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.MutualSettlementsRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.MutualSettlementsDtoStubs;
import com.trade_accounting.services.impl.Stubs.dto.RetailSalesDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.MutualSettlementsModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.utils.mapper.MutualSettlementsMapperImpl;
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
class MutualSettlementsServiceImplTest {

    @Mock
    private MutualSettlementsRepository mutualSettlementsRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ContractorRepository contractorRepository;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Spy
    private MutualSettlementsMapperImpl mutualSettlementsMapper;

    @InjectMocks
    private MutualSettlementsServiceImpl mutualSettlementsService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledMutualSettlementsDto() {
        when(mutualSettlementsRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getMutualSettlements(1L),
                                ModelStubs.getMutualSettlements(2L),
                                ModelStubs.getMutualSettlements(3L)
                        ).collect(Collectors.toList())
                );

        List<MutualSettlementsDto> mutualSettlements = mutualSettlementsService.getAll();

        assertNotNull(mutualSettlements, "Failure - expected that list of mutualSettlements not null");
        assertTrue(mutualSettlements.size() > 0, "Failure - expected that size of list of mutualSettlements greater than 0");

        for (MutualSettlementsDto mutualSettlement : mutualSettlements) {
            mutualSettlementsDtoIsCorrectlyInited(mutualSettlement);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListMutualSettlementsDto() {
        when(mutualSettlementsRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<MutualSettlementsDto> mutualSettlements = mutualSettlementsService.getAll();


        assertNotNull(mutualSettlements, "Failure - expected that list of MutualSettlements not null");
        assertEquals(0, mutualSettlements.size(), "Failure - expected that size of list of MutualSettlements equals 0");
    }

    @Test
    void getById_shouldReturnFilledMutualSettlementsDto() {
        Optional<MutualSettlements> mutualSettlementsFromRepo = Optional.of(ModelStubs.getMutualSettlements(1L));

        when(mutualSettlementsRepository.findById(1L))
                .thenReturn(mutualSettlementsFromRepo);

        MutualSettlementsDto mutualSettlements = mutualSettlementsService.getById(1L);

        assertNotNull(mutualSettlements, "failure - expected that mutualSettlements not null.");
        mutualSettlementsDtoIsCorrectlyInited(mutualSettlements);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();


//        verify(mutualSettlementsRepository).save(any(MutualSettlements.class));
//        verify(companyRepository).findById(anyLong());
//        verify(contractorRepository).findById(anyLong());
//        verify(contractRepository).findById(anyLong());
//        verify(projectRepository).findById(anyLong());
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {


//        verify(mutualSettlementsRepository).save(any(MutualSettlements.class));
//        verify(companyRepository).findById(anyLong());
//        verify(contractorRepository).findById(anyLong());
//        verify(contractRepository).findById(anyLong());
//        verify(projectRepository).findById(anyLong());
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        mutualSettlementsService.deleteById(1L);
        verify(mutualSettlementsRepository).deleteById(1L);
    }

    void mutualSettlementsDtoIsCorrectlyInited(MutualSettlementsDto mutualSettlements) {
        assertNotNull(mutualSettlements, "Fail in passed mutualSettlements");
        assertNotNull(mutualSettlements.getId(), "Fail in field 'id' of mutualSettlements");
        assertNotNull(mutualSettlements.getEmployeeId(), "Fail in field 'number' of mutualSettlements");
      //  assertNotNull(mutualSettlements.getCompanyId(), "Fail in field 'companyDto' of mutualSettlements");
        assertNotNull(mutualSettlements.getContractorId(), "Fail in field 'contractorDto' of mutualSettlements");
    }

    private void saveOrUpdate() {
        when(mutualSettlementsRepository.save(any(MutualSettlements.class)))
                .thenReturn(MutualSettlementsModelStubs.getMutualSettlements(1L));

        MutualSettlementsDto mutualSettlementsDto = mutualSettlementsService
                .create(MutualSettlementsDtoStubs.getMutualSettlementsDto(1L));

        assertEquals(1,mutualSettlementsDto.getId());
        verify(mutualSettlementsRepository).save(any(MutualSettlements.class));
    }
}
