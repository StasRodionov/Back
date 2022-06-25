package com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupDto {

    private Long id;

    private String name;

    private String sortNumber;

    private Boolean serviceGroup = false;

    private Long parentId;

    private String description;

    private String saleTax;

    private Long taxSystemId;

    private Long employeeId;

    private Long departmentId;

    public ProductGroupDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }

    public ProductGroupDto(String name, String sortNumber, Long parentId) {
        this(name, sortNumber);
        this.parentId = parentId;
    }

    public ProductGroupDto(Long id, String name, String sortNumber, Long parentId) {
        this.id = id;
        this.name = name;
        this.sortNumber = sortNumber;
        this.parentId = parentId;
    }

    public ProductGroupDto(String name, String sortNumber, Boolean serviceGroup, Long parentId, String description, String saleTax, Long taxSystemId, Long employeeId, Long departmentId) {
        this.name = name;
        this.sortNumber = sortNumber;
        this.serviceGroup = serviceGroup;
        this.parentId = parentId;
        this.description = description;
        this.saleTax = saleTax;
        this.taxSystemId = taxSystemId;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }
}
