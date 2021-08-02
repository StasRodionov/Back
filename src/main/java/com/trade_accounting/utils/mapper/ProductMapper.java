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
            @Mapping(source = "unit", target = "unitDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "productPrices", target = "productPriceDtos"),
            @Mapping(source = "taxSystem", target = "taxSystemDto"),
            @Mapping(source = "productGroup", target = "productGroupDto"),
            @Mapping(source = "attributeOfCalculationObject", target = "attributeOfCalculationObjectDto")
    })
    ProductDto toDto(Product product);

    @Mappings({
            @Mapping(source = "unitDto", target = "unit"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "productPriceDtos", target = "productPrices"),
            @Mapping(source = "taxSystemDto", target = "taxSystem"),
            @Mapping(source = "productGroupDto", target = "productGroup"),
            @Mapping(source = "attributeOfCalculationObjectDto", target = "attributeOfCalculationObject")
    })
    Product toModel(ProductDto productDto);

    List<ProductDto> toListDto(Collection<Product> products);

}
