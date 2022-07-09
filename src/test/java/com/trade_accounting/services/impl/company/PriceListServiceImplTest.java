package com.trade_accounting.services.impl.company;

import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.PriceListRepository;
import com.trade_accounting.Stubs.dto.company.PriceListDtoStubs;
import com.trade_accounting.Stubs.model.company.PriceListModelStubs;
import com.trade_accounting.repositories.company.TypeOfPriceRepository;
import com.trade_accounting.utils.mapper.company.PriceListMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Andrey Melnikov and Pavel Andrusov
 * @since 05.08.2021
 */

@ContextConfiguration(classes = PriceListServiceImpl.class)
@ExtendWith(MockitoExtension.class)
public class PriceListServiceImplTest {

    @Mock
    private PriceListRepository priceListRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private TypeOfPriceRepository typeOfPriceRepository;

    @Spy
    private PriceListMapperImpl priceListMapper;

    @InjectMocks
    private PriceListServiceImpl priceListService;

    @Test
    void getAllReturnFilledList() {
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
    void getAllReturnEmptyList() {
        when(priceListRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<PriceListDto> checkList = priceListService.getAll();

        assertNotNull(checkList, "Ошибка - список прайсов (PriceList) не должен быть null");
        assertEquals(0, checkList.size(), "Ошибка - размер списка прайсов (PriceList) должен быть = 0");
    }

    @Test
    void getById() {
        when(priceListRepository.getOne(anyLong()))
                .thenReturn(PriceListModelStubs.getPriceList(1L));

        PriceListDto priceListDto = priceListService.getById(1L);
        assertEquals(1, priceListDto.getId());
    }

    @Test
    void create() {
        priceListService.create(PriceListDtoStubs.getDto(1L));
        verify(priceListRepository).save(any(PriceList.class));
    }

    @Test
    void update() {
        priceListService.create(PriceListDtoStubs.getDto(anyLong()));
        verify(priceListRepository).save(any(PriceList.class));
    }

    @Test
    void delete() {
        priceListService.deleteById(1L);
        verify(priceListRepository).deleteById(any());
    }

    @Test
    void search() {
        when(priceListRepository.findAll(Mockito.<Specification<PriceList>>any()))
                .thenReturn(
                        Stream.of(
                                PriceListModelStubs.getPriceList(1L),
                                PriceListModelStubs.getPriceList(2L),
                                PriceListModelStubs.getPriceList(3L)
                        ).collect(Collectors.toList())
                );
        List<PriceListDto> priceLists = priceListService.search(SpecificationStubs.getPriceListSpecificationStub());
        assertNotNull(priceLists);
        assertEquals(3, priceLists.size());
    }

    @Test
    void quickSearch() {
        String searchValue = "value";
        List<PriceList> stubProductList = Stream.of(PriceListModelStubs.getPriceList(1L)).collect(Collectors.toList());
        when(priceListRepository.getBySearch(searchValue)).thenReturn(stubProductList);

        List<PriceListDto> expectedCollect = Stream.of(PriceListDtoStubs.getDto(1L)).collect(Collectors.toList());

        List<PriceListDto> factCollect = priceListService.quickSearch(searchValue);
        verify(priceListMapper).toDto(stubProductList.get(0));
        assertEquals(expectedCollect, factCollect);
    }
}
