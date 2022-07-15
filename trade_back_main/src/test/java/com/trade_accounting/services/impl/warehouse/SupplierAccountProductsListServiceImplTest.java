package com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.warehouse.SupplierAccountProductsListDtoStubs;
import com.trade_accounting.models.dto.warehouse.SupplierAccountProductsListDto;
import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.SupplierAccountProductsList;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.SupplierAccountProductsListRepository;
import com.trade_accounting.services.interfaces.company.SupplierAccountService;
import com.trade_accounting.utils.mapper.company.SupplierAccountMapper;
import com.trade_accounting.utils.mapper.warehouse.SupplierAccountProductsListMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class SupplierAccountProductsListServiceImplTest {
    @InjectMocks
    SupplierAccountProductsListServiceImpl supplierAccountProductsListService;
    @Mock
    SupplierAccountProductsListRepository supplierAccountProductsListRepository;
    @Spy
    SupplierAccountProductsListMapper mapper;
    @Mock
    ProductRepository productRepository;
    @Mock
    SupplierAccountService supplierAccountService;
    @Spy
    SupplierAccountMapper supplierAccountMapper;


    @Test
    void getAll() {
        when(supplierAccountProductsListRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getSupplierAccountProductsList(1L),
                                ModelStubs.getSupplierAccountProductsList(2L),
                                ModelStubs.getSupplierAccountProductsList(3L)
                        ).collect(Collectors.toList())
                );

        List<SupplierAccountProductsListDto> supplierAccountProductsListDto = supplierAccountProductsListService.getAll();

        assertNotNull(supplierAccountProductsListDto, "Failure - expected that list of serialNumbers not null");
        assertEquals(3, supplierAccountProductsListDto.size(), "failure - expected that size of list of serialNumbers equals 3");
        verify(supplierAccountProductsListRepository).findAll();

    }

    @Test
    void getById() {
        when(supplierAccountProductsListRepository.findById(anyLong()))
                .thenReturn(Optional.of(ModelStubs.getSupplierAccountProductsList(1L)));
        when(mapper.toDto(ModelStubs.getSupplierAccountProductsList(1L))).thenReturn(getSupplierAccountProductsListDto());

        SupplierAccountProductsListDto supplierAccountProductsListDto = supplierAccountProductsListService.getById(1L);

        supplierAccountProductsListDtoIsCorrectlyInited(supplierAccountProductsListDto);
    }

    @Test
    void create() {
        SupplierAccountProductsListDto supplierAccountProductsListDto = SupplierAccountProductsListDtoStubs.getDto(1L);

        when(mapper.toModel(supplierAccountProductsListDto)).thenReturn(getSupplierAccountProductsList());

        supplierAccountProductsListService.create(supplierAccountProductsListDto);

        verify(supplierAccountProductsListRepository).save(any(SupplierAccountProductsList.class));
    }

    @Test
    void update() {
        SupplierAccountProductsListDto supplierAccountProductsListDto = SupplierAccountProductsListDtoStubs.getDto(1L);
        when(mapper.toModel(supplierAccountProductsListDto)).thenReturn(getSupplierAccountProductsList());

        supplierAccountProductsListService.update(supplierAccountProductsListDto);

        verify(supplierAccountProductsListRepository).save(any(SupplierAccountProductsList.class));
    }

    @Test
    void deleteById() {
        supplierAccountProductsListService.deleteById(1L);
        verify(supplierAccountProductsListRepository).deleteById(anyLong());
    }

    @Test
    void search() {
        when(supplierAccountProductsListRepository.findAll(Mockito.<Specification<SupplierAccountProductsList>>any()))
                .thenReturn(new ArrayList<>());

        List<SupplierAccountProductsListDto> supplierAccountProductsListDto = supplierAccountProductsListService
                .search(SpecificationStubs.getSupplierAccountProductsListSpecificationStub());

        assertNotNull(supplierAccountProductsListDto, "failure - expected that a list of serialNumbersDtoList not null");
        assertEquals(0, supplierAccountProductsListDto.size(), "failure - expected that size of list of serialNumbersDtoList equals 0");
    }

    private void supplierAccountProductsListDtoIsCorrectlyInited(SupplierAccountProductsListDto supplierAccountProductsListDto) {
        assertNotNull(supplierAccountProductsListDto, "failure - fail in passed serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getId(), "failure - fail in field 'id' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getSupplierAccountId(), "failure - fail in field 'Code' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getProductId(), "failure - fail in field 'VendorCode' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getAmount(), "failure - fail in field 'ProductId' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getPrice(), "failure - fail in field 'WarehouseId' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getSum(), "failure - fail in field 'TypeDocument' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getPercentNds(), "failure - fail in field 'DocumentNumber' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getNds(), "failure - fail in field 'Description' into serialNumbersDto");
        assertNotNull(supplierAccountProductsListDto.getTotal(), "failure - fail in field 'Description' into serialNumbersDto");
    }

    private SupplierAccountProductsList getSupplierAccountProductsList() {
        return new SupplierAccountProductsList(
                0L,
                new SupplierAccount(),
                new Product(),
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3),
                "percentNds",
                new BigDecimal(4),
                new BigDecimal(5));
    }

    private SupplierAccountProductsListDto getSupplierAccountProductsListDto() {
        return new SupplierAccountProductsListDto(
                0L,
                1L,
                2L,
                new BigDecimal(3),
                new BigDecimal(4),
                new BigDecimal(5),
                "percentNds",
                new BigDecimal(6),
                new BigDecimal(7));
    }
}