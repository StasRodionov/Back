package com.trade_accounting.config;

import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.services.interfaces.WarehouseService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final TypeOfPriceService typeOfPriceService;
    private final RoleService roleService;
    private final WarehouseService warehouseService;

    public DataInitializer(TypeOfPriceService typeOfPriceService,
                           RoleService roleService,
                           WarehouseService warehouseService) {
        this.typeOfPriceService = typeOfPriceService;
        this.roleService = roleService;
        this.warehouseService = warehouseService;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
        initRoles();
        initWarehouses();
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto("Оптовая цена", "1"));
        typeOfPriceService.create(new TypeOfPriceDto("Розничная цена", "2"));
    }

    private void initRoles() {
        roleService.create(new RoleDto("admin", "1"));
        roleService.create(new RoleDto("user", "2"));
    }

    private void initWarehouses(){
        warehouseService.create(new WarehouseDto("Основной склад", "1"));
    }
}
