package com.trade_accounting.services.impl;

import com.trade_accounting.models.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.PurchaseHistoryOfSalesDto;
import com.trade_accounting.repositories.PurchaseHistoryOfSalesRepository;
import com.trade_accounting.services.impl.Stubs.dto.PurchaseHistoryOfSalesDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.PurchaseHistoryOfSalesStubs;
import com.trade_accounting.utils.mapper.PurchaseHistoryOfSalesMapper;
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
class PurchaseHistoryOfSalesServiceImplTest {
    @Mock
    PurchaseHistoryOfSalesRepository purchaseHistoryOfSalesRepository;

    @Spy
    PurchaseHistoryOfSalesMapper purchaseHistoryOfSalesMapper;

    @InjectMocks
    PurchaseHistoryOfSalesServiceImpl purchaseHistoryOfSalesService;

    @Test
    void getAll() {
        when(purchaseHistoryOfSalesRepository.findAll())
                .thenReturn(
                        List.of(
                                PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(1L),
                                PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(2L),
                                PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(3L)
                        )
                );
        List<PurchaseHistoryOfSalesDto> purchaseHistoryOfSalesDtos = purchaseHistoryOfSalesService.getAll();

        assertEquals(3, purchaseHistoryOfSalesDtos.size());
    }

    @Test
    void getById() {
        PurchaseHistoryOfSales test = PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(1L);

        when(purchaseHistoryOfSalesRepository.getOne(anyLong()))
                .thenReturn(PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(1L));

        PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto = purchaseHistoryOfSalesService.getById(1L);

        assertEquals(1, purchaseHistoryOfSalesDto.getId());
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
        purchaseHistoryOfSalesService.deleteById(anyLong());
        verify(purchaseHistoryOfSalesRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(purchaseHistoryOfSalesRepository.save(any(PurchaseHistoryOfSales.class)))
                .thenReturn(PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(1L));

        PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto = purchaseHistoryOfSalesService
                .create(PurchaseHistoryOfSalesDtoStubs.getDto(1L));

        assertEquals(1, purchaseHistoryOfSalesDto.getId());
        verify(purchaseHistoryOfSalesRepository).save(any(PurchaseHistoryOfSales.class));
    }
}
