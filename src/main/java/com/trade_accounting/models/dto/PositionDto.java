package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    private Long id;

    private String name;

    private String sortNumber;

    public PositionDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
