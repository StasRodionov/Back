package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.Revenue;
import com.trade_accounting.services.impl.Stubs.ModelStubs;

import java.math.BigDecimal;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getInvoicesStatus;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getProduct;

public class RevenueModelStubs {
    public static Revenue getRevenue(Long id){
        return Revenue.builder()
                .id(id)
                .product(getProduct(id))
                .description("Описание"+id)
                .itemNumber(new Integer(34))
                .amountAcceptance(new BigDecimal(198))
                .acceptance(AcceptanceModelStubs.getAcceptance(id))
                .invoiceProduct(InvoiceProductModelStubs.getInvoiceProduct(id))
                .startOfPeriodAmount(12)
                .startOfPeriodSumOfPrice(13)
                .endOfPeriodAmount(32)
                .endOfPeriodSumOfPrice(45)
                .comingAmount(2)
                .spendingAmount(55)
                .spendingSumOfPrice(45)
                .build();



    }
}
