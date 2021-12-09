package com.trade_accounting.services.impl.Stubs;

import com.trade_accounting.models.BuyersReturn;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Currency;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.ProductionTargets;
import com.trade_accounting.models.StagesProduction;
import org.springframework.data.jpa.domain.Specification;

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

    public static Specification<Currency> getCurrencySpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
    public static Specification<Product> getProductSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<ProductionTargets> getProductionTargetsSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<BuyersReturn> getBuyersReturnSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<StagesProduction> getStagesProductionSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
}
