package com.trade_accounting.utils.mapper.client;

import com.trade_accounting.models.entity.client.Role;
import com.trade_accounting.models.dto.client.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    //Role
    Role toModel(RoleDto role);

    @Mapping(target = "id", source = "role.id")
    RoleDto toDto(Role role);
}
