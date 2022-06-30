package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.models.entity.company.PriceListProduct;
import com.trade_accounting.models.entity.company.PriceListProductPercents;
import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceListMapper {
    //PriceList
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "productsIds", target = "products")
    @Mapping(source = "typeOfPriceId", target = "typeOfPrice.id")
    @Mapping(source = "percentsIds", target = "percents")
    PriceList toModel(PriceListDto priceListDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "productsIds", source = "products")
    @Mapping(target = "typeOfPriceId", source = "typeOfPrice.id")
    @Mapping(source = "percents", target = "percentsIds")
    PriceListDto toDto(PriceList priceList);

    default Long productToLong(PriceListProduct product) {
        return product.getId();
    }

    default PriceListProduct longToPriceListProduct(Long id) {
        return PriceListProduct.builder()
                .id(id)
                .build();
    }

    default Long productToLong(PriceListProductPercents product) {
        return product.getId();
    }

    default PriceListProductPercents longToPriceListProductPrices(Long id) {
        return PriceListProductPercents.builder()
                .id(id)
                .build();
    }
}
