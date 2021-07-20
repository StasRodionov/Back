package com.trade_accounting.services.impl;

import com.trade_accounting.models.Payout;
import com.trade_accounting.models.dto.PayoutDto;
import com.trade_accounting.repositories.PayoutRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PayoutServiceImplTest {

    @Mock
    private PayoutRepository payoutRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private PayoutServiceImpl payoutService;


    @Test
    void getAll_shouldReturnListFilledPayoutDto() {
        when(payoutRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getPayout(1L),
                                ModelStubs.getPayout(2L),
                                ModelStubs.getPayout(3L)
                        )
                                .collect(Collectors.toList())
                );
        List<PayoutDto> payouts = payoutService.getAll();
        assertNotNull(payouts, "Failure - expected that list of payout not null");
        assertTrue(payouts.size() > 0, "failure - expected that size of list of payout greater than 0");
    }

    @Test
    void getAll_shouldReturnEmptyListPayoutDto() {
        when(payoutRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<PayoutDto> payouts = payoutService.getAll();

        assertNotNull(payouts, "Failure - expected that list of payout not null");
        assertEquals(0, payouts.size(), "failure - expected that size of list of company equals 0");
        verify(payoutRepository).findAll();
    }

    @Test
    void getById_shouldReturnFilledPayoutDto() {
        when(payoutRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getPayout(1L));

        PayoutDto payoutDto = payoutService.getById(1L);

        assertEquals(1, payoutDto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        payoutService.create(DtoStubs.getPayoutDto(1L));

        verify(payoutRepository).save(any(Payout.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        payoutService.update(DtoStubs.getPayoutDto(1L));

        verify(payoutRepository).save(any(Payout.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        payoutService.deleteById(1L);
        verify(payoutRepository).deleteById(anyLong());
    }
}
