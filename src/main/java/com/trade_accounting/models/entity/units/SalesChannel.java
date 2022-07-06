package com.trade_accounting.models.entity.units;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales_channel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "general_access")
    private boolean generalAccess;

    @Column(name = "department_owner")
    private String departmentOwner;

    @Column(name = "employee_owner")
    private String employeeOwner;

    @Column(name = "date_of_change")
    private String dateOfChange;

    @Column(name = "employee_change")
    private String employeeChange;

    public SalesChannel(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

}
