package com.trade_accounting.services.interfaces.client;

import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.services.interfaces.util.AbstractService;

public interface DepartmentService extends AbstractService<DepartmentDto> {

    DepartmentDto getByName(String name);

    Department createDepartment(DepartmentDto departmentDto);

    Department updateDepartment(DepartmentDto departmentDto);

    void deleteByIdDepartment(Long id);
}
