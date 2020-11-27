package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.RoleDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    RoleDto getById(Long id);

    void create(RoleDto roleDto);

    void update(RoleDto roleDto);

    void deleteById(Long id);
}
