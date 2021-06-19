package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnToSuppliersDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Warehouse warehouse;

    @NotNull
    private Company company;

    @NotNull
    private Contractor contractor;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

}
