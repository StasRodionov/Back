package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class DtoStubs {
    private static DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static CompanyDto getCompanyDto(Long id) {
        return dtoMapper.companyToCompanyDto(
                ModelStubs.getCompany(id)
        );
    }

    public static RoleDto getRoleDto(Long id) {
        return dtoMapper.roleToRoleDto(
                ModelStubs.getRole(id)
        );
    }
}
