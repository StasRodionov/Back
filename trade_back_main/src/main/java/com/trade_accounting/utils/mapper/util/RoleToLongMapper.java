package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.client.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleToLongMapper {
    default Long toLong(Role role) {
        return role.getId();
    }
}
