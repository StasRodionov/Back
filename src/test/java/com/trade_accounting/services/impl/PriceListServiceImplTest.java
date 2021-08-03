package com.trade_accounting.services.impl;

import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.repositories.PriceListRepository;
import com.trade_accounting.services.impl.Stubs.dto.PriceListDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.PriceListModelStubs;
import com.trade_accounting.utils.mapper.PriceListMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceListServiceImplTest {

    @Mock
    private PriceListRepository priceListRepository;

    @Spy
    private PriceListMapper priceListMapper;

    @InjectMocks
    private PriceListServiceImpl priceListService;

    @Test
    void getAll_shouldReturnListFilledPriceListDto() {
        when(priceListRepository.findAll())
                .thenReturn(
                        Stream.of(PriceListModelStubs.getPriceList(1L)
                                , PriceListModelStubs.getPriceList(2L)
                                , PriceListModelStubs.getPriceList(3L)
                        ).collect(Collectors.toList()));

        List<PriceListDto> checkList = priceListService.getAll();

        assertNotNull(checkList, "Ошибка - список прайсов (PriceList) не должен быть равен null");
        assertEquals(3L, checkList.size(), "Ошибка - ожидается размер списка прайсов (PriceList) равным 3");

    }

    @Test
    void getAll_shouldReturnEmptyListPriceListDto() {
        when(priceListRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<PriceListDto> checkList = priceListService.getAll();

        assertNotNull(checkList, "Ошибка - список прайсов (PriceList) не должен быть null");
        assertEquals(0, checkList.size(), "Ошибка - размер списка прайсов (PriceList) должен быть = 0");
        verify(priceListRepository).findAll(); // сделать проверку на кол-во раз вызыва метода priceListRepository.findAll()
    }

    @Test
    void getById_shouldReturnFilledPriceListDto() {
        when(priceListRepository.getOne(anyLong()))
                .thenReturn(PriceListModelStubs.getPriceList(1L));

        PriceListDto priceListDto = priceListService.getById(1L);
        assertEquals(1, priceListDto.getId());
    }

    @Test
    void createCheckPriceList() {
        priceListService.create(PriceListDtoStubs.getDto(1L));
        verify(priceListRepository).save(any(PriceList.class));
    }

    @Test
    void updateCheckPriceList() {
        priceListService.update(PriceListDtoStubs.getDto(1L));
        verify(priceListRepository).save(any(PriceList.class));
    }

    @Test
    void deleteByIdCheckPriceList() {
        priceListService.deleteById(1L);
        verify(priceListRepository).deleteById(any());
    }
}
