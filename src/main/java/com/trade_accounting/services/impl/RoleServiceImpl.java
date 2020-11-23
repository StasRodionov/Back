package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
