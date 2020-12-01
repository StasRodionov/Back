package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractorGroupDto {

    private Long id;

    private String name;

    private String sortNumber;

    public ContractorGroupDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
