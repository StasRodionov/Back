package com.trade_accounting.models.dto.warehouse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfPackingDto {

    private Long id;

    private String name;

    private String sortNumber;
}
