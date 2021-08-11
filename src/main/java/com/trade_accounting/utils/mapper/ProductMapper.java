package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default Product toModel(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .volume(productDto.getVolume())
                .weight(productDto.getWeight())
                .saleTax(productDto.getSaleTax())
                .countryOrigin(productDto.getCountryOrigin())
                .description(productDto.getDescription())
                .archive(productDto.getArchive())
                .service(productDto.getService())
                .purchasePrice(productDto.getPurchasePrice())
                .build();
    }

    default ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        if (product == null) {
            return null;
        } else {
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setVolume(product.getVolume());
            productDto.setWeight(product.getWeight());
            productDto.setSaleTax(product.getSaleTax());
            productDto.setCountryOrigin(product.getCountryOrigin());
            productDto.setDescription(product.getDescription());
            productDto.setArchive(product.getArchive());
            productDto.setService(product.getService());
            productDto.setPurchasePrice(product.getPurchasePrice());
//            productDto.setAttributeOfCalculationObjectId(product.getAttributeOfCalculationObject().getId());
//            productDto.setContractorId(product.getContractor().getId());
//            productDto.setProductGroupId(product.getProductGroup().getId());
//            productDto.setTaxSystemId(product.getTaxSystem().getId());
//            productDto.setUnitId(product.getUnit().getId());
            return productDto;
        }
    }

    List<ProductDto> toListDto(Collection<Product> products);

//            default List<ProductDto> toListDto(Collection<Product> products) {
//                ProductDto productDto1 = new ProductDto();
//                if (products == null) {
//                    return null;
//                } else {
//                    productDto1.setAttributeOfCalculationObjectId();
//                }
//            }
}