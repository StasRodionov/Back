package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechCardDto {
    private Long id;

    private String name;

    private String comment;

    private Long production_costs;

    private String group;

    private String finished_products;

    private String materials;
}
