package com.trade_accounting.models.dto.company;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTaxDto {

    private Long id;

    private String name;

    private String sortNumber;
}
