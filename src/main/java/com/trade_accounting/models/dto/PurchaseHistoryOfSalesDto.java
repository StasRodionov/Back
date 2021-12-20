package com.trade_accounting.models.dto;

import com.trade_accounting.models.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseHistoryOfSalesDto {

    @NotNull
    private Long id;

    private BigDecimal sumOfProducts;

    private ProductPrice productPrice;

    private BigDecimal productMargin;

    private BigDecimal productProfitMargin;

    private Long productSalesPerDay;
}