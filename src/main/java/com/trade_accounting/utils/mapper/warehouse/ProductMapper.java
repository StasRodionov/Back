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

    @Mapping(target = "unit.id", source = "unitId")
    @Mapping(target = "contractor.id", source = "contractorId")
    @Mapping(target = "productPrices", source = "productPriceIds")
    @Mapping(target = "taxSystem.id", source = "taxSystemId")
    @Mapping(target = "images", source = "imageDtos")
    @Mapping(target = "files", source = "fileDtos")
    @Mapping(target = "productGroup.id", source = "productGroupId")
    @Mapping(target = "attributeOfCalculationObject.id", source = "attributeOfCalculationObjectId")
    @Mapping(target = "typeOfPacking.id", source = "typeOfPackingId")
    @Mapping(target = "saleTaxEntity.id", source = "saleTaxId")
    Product toModel(ProductDto productDto);

    @Mapping(target = "unitId", source = "unit.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "productPriceIds", source = "productPrices")
    @Mapping(target = "taxSystemId", source = "taxSystem.id")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "fileDtos", source = "files")
    @Mapping(target = "productGroupId", source = "productGroup.id")
    @Mapping(target = "attributeOfCalculationObjectId", source = "attributeOfCalculationObject.id")
    @Mapping(target = "typeOfPackingId", source = "typeOfPacking.id")
    @Mapping(target = "saleTaxId", source = "saleTaxEntity.id")
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