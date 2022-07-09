package com.trade_accounting.Stubs.model.purchases;

import com.trade_accounting.Stubs.model.company.CompanyModelStubs;
import com.trade_accounting.Stubs.model.warehouse.WarehouseModelStubs;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.Stubs.ModelStubs;

public class PurchaseControlModelStubs {
    public static PurchaseControl getPurchaseControl(Long id) {
        return PurchaseControl.builder()
                .id(id)
                .product(ModelStubs.getProduct(1L))
                .company(CompanyModelStubs.getCompany(1L))
                .warehouse(WarehouseModelStubs.getWarehouse(1L))
                .productCode(10000L)
                .articleNumber(1000L)
                .productMeasure("test")
                .productQuantity(100L)
                .historyOfSales(PurchaseHistoryOfSalesStubs.getPurchaseHistoryOfSales(1L))
                .currentBalance(PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(1L))
                .forecast(PurchaseForecastStubs.getPurchaseForecast(1L))
                .build();

    }
}
