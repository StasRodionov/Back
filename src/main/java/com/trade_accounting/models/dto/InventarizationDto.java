package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarizationDto {

    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long companyId;

    private Boolean status = false;

    private String comment;

    //LIST inventarization products!!!

}
