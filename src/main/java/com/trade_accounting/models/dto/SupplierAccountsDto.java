package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAccountsDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long nameCompany;

    @NotNull
    private Long nameWarehouse;

    @NotNull
    private Long numberContract;

    @NotNull
    private Long nameContractor;

    private boolean isSpend;

    private String comment;

}
