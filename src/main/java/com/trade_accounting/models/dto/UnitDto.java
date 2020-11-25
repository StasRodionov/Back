package com.trade_accounting.models.dto;

import com.trade_accounting.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDto {

    private Long id;

    private String shortName;

    private String fullName;

    private String sortNumber;

}
