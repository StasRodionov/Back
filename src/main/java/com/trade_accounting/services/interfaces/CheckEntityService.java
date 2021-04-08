package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;

public interface CheckEntityService {
    void checkExistsUnitById(Long unitId);
    void checkExistsEmployeeById(Long employeeId);
    void checkExistsWarehouseById(Long warehouseId);
    void checkForBadEmployee(EmployeeDto employee);
    void checkExistsTaskById(Long taskId);
    void checkExistsTaskCommentById(Long taskCommentId);
    void checkExistsContractorGroupById(Long contractorGroupId);
}
