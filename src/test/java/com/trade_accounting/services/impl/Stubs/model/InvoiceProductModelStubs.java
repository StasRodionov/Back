package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.services.impl.Stubs.ModelStubs;

import java.math.BigDecimal;

public class InvoiceProductModelStubs {
    public static InvoiceProduct getInvoiceProduct(Long id) {
        return InvoiceProduct.builder()
                .id(id)
                .invoice(ModelStubs.getInvoice(id))
                .product(ModelStubs.getProduct(id))
                .amount(BigDecimal.ONE)
                .price(BigDecimal.ONE)
                .build();
    }
}
