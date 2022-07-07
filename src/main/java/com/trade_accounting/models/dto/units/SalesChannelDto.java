package com.trade_accounting.models.dto.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesChannelDto {

    private Long id;

    private String name;

    private String type;

    private String description;

    private boolean generalAccess;

    private String departmentOwner;

    private String employeeOwner;

    private String dateOfChange;

    private String employeeChange;

//    public SalesChannelDto(String name, String type, String description) {
//        this.name = name;
//        this.type = type;
//        this.description = description;
//    }
//
//    public SalesChannelDto(Long id, String name, String type, String description) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.description = description;
//    }

//    public SalesChannelDto(Long id, String name, String type, String description, boolean generalAccess, String departmentOwner, String employeeOwner, String dateOfChange, String employeeChange) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.description = description;
//        this.generalAccess = generalAccess;
//        this.departmentOwner = departmentOwner;
//        this.employeeOwner = employeeOwner;
//        this.dateOfChange = dateOfChange;
//        this.employeeChange = employeeChange;
//    }
}
