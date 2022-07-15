package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.entity.warehouse.Inventarization;
import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarizationMapper {
    //Inventarization
    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    Inventarization toModel(InventarizationDto inventarizationDto);

    @Mapping(source = "date", target = "date", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "inventarizationProductIds", source = "inventarizationProducts")
    InventarizationDto toDto(Inventarization inventarization);

    default Long inventarizationProductToLong(InventarizationProduct inventarizationProduct) {
        return inventarizationProduct.getId();
    }
}
