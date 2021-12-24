package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ProductPriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {

    default ProductPriceDto toDto(ProductPrice productPrice) {
        if ( productPrice == null ) {
            return null;
        }

        ProductPriceDto productPriceDto = new ProductPriceDto();

        productPriceDto.setTypeOfPriceId( productPriceTypeOfPriceId( productPrice ) );
        productPriceDto.setId( productPrice.getId() );
        productPriceDto.setValue( productPrice.getValue() );

        return productPriceDto;
    }

    default ProductPrice toModel(ProductPriceDto productPriceDto) {
        if ( productPriceDto == null ) {
            return null;
        }

        ProductPrice productPrice = new ProductPrice();

        productPrice.setTypeOfPrice( productPriceDtoToTypeOfPrice( productPriceDto ) );
        productPrice.setId( productPriceDto.getId() );
        productPrice.setValue( productPriceDto.getValue() );

        return productPrice;
    }

    private Long productPriceTypeOfPriceId(ProductPrice productPrice) {
        if ( productPrice == null ) {
            return null;
        }
        TypeOfPrice typeOfPrice = productPrice.getTypeOfPrice();
        if ( typeOfPrice == null ) {
            return null;
        }
        Long id = typeOfPrice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private TypeOfPrice productPriceDtoToTypeOfPrice(ProductPriceDto productPriceDto) {
        if ( productPriceDto == null ) {
            return null;
        }

        TypeOfPrice typeOfPrice = new TypeOfPrice();

        typeOfPrice.setId( productPriceDto.getTypeOfPriceId() );

        return typeOfPrice;
    }
}
