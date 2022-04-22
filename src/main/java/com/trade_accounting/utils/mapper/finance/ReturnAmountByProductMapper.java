package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.ReturnAmountByProduct;
import com.trade_accounting.models.dto.finance.ReturnAmountByProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReturnAmountByProductMapper {
    //ReturnAmountByProduct
//    @Mapping(source = "productId", target = "product.id")
//    @Mapping(source = "invoiceId", target = "invoice.id")
//    @Mapping(source = "contractorId", target = "contractor.id")
    ReturnAmountByProduct toModel(ReturnAmountByProductDto returnAmountByProductDto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "invoiceId", source = "invoice.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    ReturnAmountByProductDto toDto(ReturnAmountByProduct returnAmountByProduct);

}
