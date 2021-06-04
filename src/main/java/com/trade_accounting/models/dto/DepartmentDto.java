package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;

    private String sortNumber;

    public DepartmentDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
