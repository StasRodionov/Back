package com.trade_accounting.services.impl;

import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final DtoMapper dtoMapper;

    public RoleServiceImpl(RoleRepository roleRepository, DtoMapper dtoMapper) {
        this.roleRepository = roleRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(dtoMapper::roleToRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getById(Long id) {
        return dtoMapper.roleToRoleDto(
                roleRepository.findById(id).orElse(new Role())
        );
    }

    @Override
    public RoleDto getByName(String name) {
        return dtoMapper.roleToRoleDto(
                roleRepository.findByName(name).orElse(new Role())
        );
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = roleRepository.save(
                dtoMapper.roleDtoToRole(roleDto)
        );

        return dtoMapper.roleToRoleDto(role);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        return create(roleDto);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
