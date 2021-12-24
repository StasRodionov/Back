package com.trade_accounting.services.impl;

import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.repositories.ProductPriceRepository;
import com.trade_accounting.repositories.RetailSalesRepository;
import com.trade_accounting.services.impl.Stubs.dto.ProductPriceDtoStubs;
import com.trade_accounting.services.impl.Stubs.dto.RetailSalesDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.ProductPriceModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.services.interfaces.ProductPriceService;
import com.trade_accounting.utils.mapper.ProductPriceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductPriceServiceImplTest {

    @Mock
    ProductPriceRepository productPriceRepository;

    @InjectMocks
    ProductPriceServiceImpl productPriceService;

    @Spy
    ProductPriceMapper productPriceMapper;

    @Test
    void getAll() {
        when(productPriceRepository.findAll())
                .thenReturn(List.of(ProductPriceModelStubs.getProductPrice(1L)));
        List<?> list = productPriceService.getAll();
        assertNotNull(list);
        assertEquals(1, list.size());
    }

    @Test
     void getById() {
        Optional<ProductPrice> productPrice = Optional.of(ProductPriceModelStubs.getProductPrice(1L));
        when(productPriceRepository.findById(anyLong())).thenReturn(productPrice);

        ProductPriceDto productPriceDto = productPriceService.getById(1L);
        System.out.println(productPriceDto);
        assertEquals(1, productPriceDto.getId());
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
    void delete_shouldPassInstructionsSuccessfulDelete() {
        productPriceRepository.deleteById(anyLong());
        verify(productPriceRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(productPriceRepository.save(any(ProductPrice.class))).thenReturn(ProductPriceModelStubs.getProductPrice(1L));
        ProductPriceDto productPriceDto = productPriceService.create(ProductPriceDtoStubs.getDto(1L));
        assertEquals(1,productPriceDto.getId());
        verify(productPriceRepository).save(any(ProductPrice.class));
    }
}

