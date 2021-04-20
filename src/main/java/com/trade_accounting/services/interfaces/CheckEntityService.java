package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.EmployeeDto;

public interface CheckEntityService {
    void checkExistsUnitById(Long unitId);
    void checkExistsEmployeeById(Long employeeId);
    void checkExistsWarehouseById(Long warehouseId);
    void checkForBadEmployee(EmployeeDto employee);
    void checkExistsTaskById(Long taskId);
    void checkExistsTaskCommentById(Long taskCommentId);
    void checkExistsContractorGroupById(Long contractorGroupId);
    void checkExistsCurrencyById(Long currencyId);
    void checkExistCompanyById(Long companyId);
    void checkForBadCompany(CompanyDto company);
    void checkExistsRetailStoreById(Long RetailStoreId);
}
