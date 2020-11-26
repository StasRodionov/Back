package com.trade_accounting.services.impl;

import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.getAll();
    }

    @Override
    public RoleDto getById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public void create(RoleDto roleDto) {
        Role role = new Role(roleDto.getId(), roleDto.getName(), roleDto.getSortNumber());
        roleRepository.save(role);
    }

    @Override
    public void update(RoleDto roleDto) {
        Role role = roleRepository.getOne(roleDto.getId());
        role.setName(roleDto.getName());
        role.setSortNumber(roleDto.getSortNumber());
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
