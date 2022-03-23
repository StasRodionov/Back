package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesSubGoodsForSaleMapper {
    //SalesSubGoodsForSale
    SalesSubGoodsForSale toModel(SalesSubGoodsForSaleDto salesSubGoodsForSaleDto);

    SalesSubGoodsForSaleDto toDto(SalesSubGoodsForSale salesSubGoodsForSale);
}
