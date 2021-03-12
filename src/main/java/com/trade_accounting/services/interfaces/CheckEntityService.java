package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;

public interface CheckEntityService {
    void checkExistsUnitById(Long unitId);
    void checkForBadEmployee(EmployeeDto employee);
}
