package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.SupplierAccountDto;

import java.util.List;

public interface SupplierAccountService extends AbstractService<SupplierAccountDto>{

    List<SupplierAccountDto> searchByNameFilter(String nameFilter);
}
