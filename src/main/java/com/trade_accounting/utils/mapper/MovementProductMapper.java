package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.MovementProduct;
import com.trade_accounting.models.dto.MovementProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MovementProductMapper {
    //    MovementProduct
//    @Mappings({
//
//    })
// default MovementProduct toMovementProduct(MovementProductDto movementDto) {
//        if ( movementDto == null ) {
//            return null;
//        }
//
//        MovementProduct.MovementProductBuilder movementProduct = MovementProduct.builder();
//
//        movementProduct.id( movementDto.getId() );
//        movementProduct.amount( movementDto.getAmount() );
//        movementProduct.price( movementDto.getPrice() );
//
//        return movementProduct.build();
//    }

    @Mapping(source = "product.id", target = "productId")
    MovementProductDto toMovementProductDto(MovementProduct movement);
//    @Mapping(source = "product.id", target = "productId")
    MovementProduct toMovementProduct(MovementProductDto movementDto);
}
