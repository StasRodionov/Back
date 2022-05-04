package com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.warehouse.SupplierAccountProductsListDto;
import com.trade_accounting.utils.mapper.warehouse.SupplierAccountProductsListMapper;
import org.mapstruct.factory.Mappers;

public class SupplierAccountProductsListDtoStubs {
    private static final SupplierAccountProductsListMapper mapper = Mappers.getMapper(SupplierAccountProductsListMapper.class);

    public static SupplierAccountProductsListDto getDto(Long id) {
        return mapper.toDto(ModelStubs.getSupplierAccountProductsList(id));
    }
}
