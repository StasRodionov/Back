package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //Product
    Product toModel(ProductDto productDto);

    ProductDto toDto(Product product);

    List<ProductDto> toListDto(Collection<Product> products);
}