package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.ShipmentProduct;

import java.math.BigDecimal;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;

public class ShipmentProductModelStubs {
    public static ShipmentProduct getShipmentProduct(Long id){
        return ShipmentProduct.builder()
                .id(1L)
                .amount(new BigDecimal(17))
                .price(new BigDecimal(199))
                .product(getProduct(2L))
                .build();
    }

}
