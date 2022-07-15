package com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.company.TaxSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_groups")
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sort_number")
    private String sortNumber;

    @Column(name = "service_group")
    private Boolean serviceGroup = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_group", referencedColumnName = "id")
    private ProductGroup parent;

    @Column(name = "description")
    private String description;

    @Column(name = "sale_tax")
    private String saleTax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_system_id", referencedColumnName = "id")
    private TaxSystem taxSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;


    public ProductGroup(String name, String sortNumber, ProductGroup parentGroup) {
        this(name, sortNumber);
        this.parent = parentGroup;
    }

    public ProductGroup(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }

    public ProductGroup(Long id, String name, String sortNumber, ProductGroup parentGroup) {
        this.id = id;
        this.name = name;
        this.sortNumber = sortNumber;
        this.parent = parentGroup;
    }

    public ProductGroup(String name, String sortNumber, Boolean serviceGroup) {
        this.name = name;
        this.sortNumber = sortNumber;
        this.serviceGroup = serviceGroup;
    }

    public ProductGroup(String name, String sortNumber, Boolean serviceGroup, ProductGroup parentGroup) {
        this(name, sortNumber, serviceGroup);
        this.parent = parentGroup;
    }

    public ProductGroup(String name, String sortNumber, Boolean serviceGroup, ProductGroup parent, String description, String saleTax, TaxSystem taxSystem, Employee employee, Department department) {
        this.name = name;
        this.sortNumber = sortNumber;
        this.serviceGroup = serviceGroup;
        this.parent = parent;
        this.description = description;
        this.saleTax = saleTax;
        this.taxSystem = taxSystem;
        this.employee = employee;
        this.department = department;
    }
}
