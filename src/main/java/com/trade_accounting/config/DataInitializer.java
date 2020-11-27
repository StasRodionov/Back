package com.trade_accounting.config;

import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final TypeOfPriceService typeOfPriceService;
    private final RoleService roleService;

    public DataInitializer(TypeOfPriceService typeOfPriceService,
                           RoleService roleService) {
        this.typeOfPriceService = typeOfPriceService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
        initRoles();
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto("Оптовая цена", "1"));
        typeOfPriceService.create(new TypeOfPriceDto("Розничная цена", "2"));
    }

    private void initRoles() {
        roleService.create(new RoleDto("admin", "1"));
        roleService.create(new RoleDto("user", "2"));
    }
}
