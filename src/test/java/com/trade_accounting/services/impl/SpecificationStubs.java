package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SpecificationStubs {
    public static Specification<Employee> getEmployeeSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<Company> getCompanySpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<Contract> getContractSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

}
