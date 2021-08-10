package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //Product
    @Mappings({
            @Mapping(source = "unit", target = "unitId"),
            @Mapping(source = "contractor", target = "contractorId"),
            @Mapping(source = "productPrices", target = "productPriceIds"),
            @Mapping(source = "taxSystem", target = "taxSystemId"),
            @Mapping(source = "productGroup", target = "productGroupId"),
            @Mapping(source = "attributeOfCalculationObject", target = "attributeOfCalculationObjectId")
    })
    ProductDto toDto(Product product);

    @Mappings({
            @Mapping(source = "unitId", target = "unit"),
            @Mapping(source = "contractorId", target = "contractor"),
            @Mapping(source = "productPriceIds", target = "productPrices"),
            @Mapping(source = "taxSystemId", target = "taxSystem"),
            @Mapping(source = "productGroupId", target = "productGroup"),
            @Mapping(source = "attributeOfCalculationObjectId", target = "attributeOfCalculationObject")
    })
    Product toModel(ProductDto productDto);

    List<ProductDto> toListDto(Collection<Product> products);

}
