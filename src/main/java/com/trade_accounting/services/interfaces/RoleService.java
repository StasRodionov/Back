package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    RoleDto getById(Long id);

    RoleDto getByName(String name);

    RoleDto create(RoleDto roleDto);

    RoleDto update(RoleDto roleDto);

    void deleteById(Long id);
}
