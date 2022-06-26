package com.trade_accounting.utils.mapper.warehouse;


import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.utils.mapper.util.FileMapper;
import com.trade_accounting.utils.mapper.util.ImageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, FileMapper.class})
public interface ProductMapper {
    //Product
    Product toModel(ProductDto productDto);

    @Mapping(target = "unitId", source = "unit.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "productPriceIds", source = "productPrices")
    @Mapping(target = "taxSystemId", source = "taxSystem.id")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "fileDtos", source = "files")
    @Mapping(target = "productGroupId", source = "productGroup.id")
    @Mapping(target = "attributeOfCalculationObjectId", source = "attributeOfCalculationObject.id")
    ProductDto toDto(Product product);

    default Long productPriceToLong(ProductPrice productPrice) {
        return productPrice.getId();
    }

    default ProductPrice longToProductPrice(Long id) {
        return ProductPrice.builder()
                .id(id)
                .build();
    }

    List<ProductDto> toListDto(Collection<Product> products);
}