package com.trade_accounting.models.dto;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StagesProductionDto {

    private Long id;

    private String name;

    private String description;

    private Department department;

    private Employee employee;

    public void setDepartment(Department department) {
    }

    public void setEmployee(Employee employee) {
    }
}
