package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.TechnicalCardProduction;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getProduct;

public class TechnicalCardProductionServiceModelStubs {
    public static TechnicalCardProduction getTechnicalCardProduction(Long id){
        return TechnicalCardProduction.builder()
                .id(id)
                .amount(id)
                .product(getProduct(id))
                .build();
    }
}
