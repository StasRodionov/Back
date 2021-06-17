package com.trade_accounting.services.impl;

import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.CorrectionProductDto;
import com.trade_accounting.repositories.CorrectionProductRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.interfaces.CorrectionProductService;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CorrectionProductServiceImplTest {

    @Mock
    CorrectionProductRepository correctionProductRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    DtoMapperImpl dtoMapper;

    @InjectMocks
    CorrectionProductServiceImpl correctionProductService;

    @Test
    void getAll() {
        when(correctionProductRepository.findAll())
                .thenReturn(List.of(ModelStubs.getCorrectionProduct(1L),
                        ModelStubs.getCorrectionProduct(2L),
                        ModelStubs.getCorrectionProduct(3L)));

        List<CorrectionProductDto> correctionProductDtos = correctionProductService.getAll();
        assertEquals(3, correctionProductDtos.size());
    }

    @Test
    void getById() {
        Optional<CorrectionProduct> correctionProduct = Optional.of(ModelStubs.getCorrectionProduct(1L));

        when(correctionProductRepository.findById(anyLong())).thenReturn(correctionProduct);

        CorrectionProductDto correctionProductDto = correctionProductService.getById(1L);
        assertEquals(1L, correctionProductDto.getId());
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
        correctionProductService.deleteById(anyLong());
        verify(correctionProductRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(correctionProductRepository.save(any())).thenReturn(ModelStubs.getCorrectionProduct(1L));
        correctionProductService.create(DtoStubs.getCorrectionProductDto(1L));
        verify(correctionProductRepository).save(any(CorrectionProduct.class));
    }
}