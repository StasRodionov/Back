package com.trade_accounting.services.impl;

import com.trade_accounting.models.ShipmentProduct;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.models.dto.ShipmentProductDto;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.ShipmentProductRepository;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.services.impl.Stubs.model.ShipmentProductModelStubs;
import com.trade_accounting.utils.mapper.ShipmentProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShipmentProductServiceImplTest {

    @Mock
    ShipmentProductRepository shipmentProductRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    ShipmentProductMapper shipmentProductMapper;

    @InjectMocks
    ShimpentProductServiceImpl shimpentProductService;

    @Test
    void getAll() {
        when(shipmentProductRepository.findAll()).thenReturn(
                List.of(ShipmentProductModelStubs.getShipmentProduct(1L))
        );

        List<ShipmentProductDto> listInv = shimpentProductService.getAll();
        assertNotNull(listInv, "failure - expected that a list of bankAccountDto not null");
        assertEquals(1, listInv.size(), "failure - expected that a list of bankAccountDto grater than 0");
    }

}
