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
            @Mapping(source = "unit.id", target = "unitId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "productPrices.empty", target = "productPriceIds"),
            @Mapping(source = "taxSystem.id", target = "taxSystemId"),
            @Mapping(source = "productGroup.id", target = "productGroupId"),
            @Mapping(source = "attributeOfCalculationObject.id", target = "attributeOfCalculationObjectId")
    })
    ProductDto toDto(Product product);

    @Mappings({
            @Mapping(source = "unitId", target = "unit.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "productPriceIds", target = "productPrices.id"),
            @Mapping(source = "taxSystemId", target = "taxSystem.id"),
            @Mapping(source = "productGroupId", target = "productGroup.id"),
            @Mapping(source = "attributeOfCalculationObjectId", target = "attributeOfCalculationObject.id")
    })
    Product toModel(ProductDto productDto);

    List<ProductDto> toListDto(Collection<Product> products);

}
