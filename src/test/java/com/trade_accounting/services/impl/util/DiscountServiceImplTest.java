package com.trade_accounting.services.impl.util;

import com.trade_accounting.Stubs.model.util.DiscountModelStubs;
import com.trade_accounting.models.dto.util.DiscountDto;
import com.trade_accounting.models.entity.util.Discount;
import com.trade_accounting.repositories.util.DiscountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceImplTest {

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private DiscountServiceImpl discountService;

    @Test
    void getAll() {
        when(discountRepository.findAll()).thenReturn(
                List.of(
                        DiscountModelStubs.getDiscount(1L),
                        DiscountModelStubs.getDiscount(2L)
                )
        );

        List<DiscountDto> dtos = discountService.getAll();
        assertEquals(2, dtos.size(), "Количество елементов в списке не совпадаает");
        assertEquals(1L, dtos.get(0).getId(), "ID или порядок элементов не совпаадает");
    }

    @Test
    void getById() {
        when(discountRepository.getOne(anyLong())).thenReturn(DiscountModelStubs.getDiscount(1L));
        DiscountDto dto = discountService.getById(1L);
        assertEquals(1L, dto.getId());
    }

    @Test
    void saveOrUpdate() {
        when(discountRepository.save(any(Discount.class))).thenReturn(DiscountModelStubs.getDiscount(1L));
        DiscountDto dto = discountService.create(DiscountModelStubs.getDiscountDto(1L));
        assertEquals(1L, dto.getId());
        verify(discountRepository).save(any(Discount.class));
    }

    @Test
    void delete() {
        discountService.deleteById(anyLong());
        verify(discountRepository).deleteById(anyLong());
    }
}
