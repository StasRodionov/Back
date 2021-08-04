package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.AccessParameters;
import com.trade_accounting.models.dto.AccessParametersDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessParametersMapper {

    //AccessParameters
    default AccessParametersDto toDto(AccessParameters accessParameters) {
        if (accessParameters == null) {
            return null;
        } else {
            return new AccessParametersDto(
                    accessParameters.getId(),
                    accessParameters.getGeneralAccess(),
                    accessParameters.getEmployee().getId(),
                    accessParameters.getDepartment().getId()
            );
        }
    }

    default AccessParameters toModel(AccessParametersDto accessParametersDto,
                                     EmployeeRepository employeeRepository,
                                     DepartmentRepository departmentRepository,
                                     DepartmentMapper departmentMapper,
                                     EmployeeMapper employeeMapper) {
        if (accessParametersDto == null) {
            return null;
        }
        return AccessParameters.builder().id(accessParametersDto.getId()).generalAccess(accessParametersDto.getGeneralAccess())
                .employee(employeeMapper.toModel(employeeRepository.getById(accessParametersDto.getEmployeeId())))
                .department(departmentMapper.toModel(departmentRepository.getById(accessParametersDto.getDepartmentId()))).build();
    }
}
