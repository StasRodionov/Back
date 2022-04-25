package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceListMapper {
    //PriceList
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "productsIds", target = "products")
    PriceList toModel(PriceListDto priceListDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "productsIds", source = "products")
    PriceListDto toDto(PriceList priceList);

    default Long productToLong(Product product) {
        return product.getId();
    }

    default Product longToInternalOrderProduct(Long id) {
        return Product.builder()
                .id(id)
                .build();
    }
}
