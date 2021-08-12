package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListDto {

    private Long id;

    @NotNull
    private String number;

    private LocalDateTime time;

    @NotNull
    private CompanyDto companyDto;

    private Long sent;

    private Long printed;

    private String commentary;
}
