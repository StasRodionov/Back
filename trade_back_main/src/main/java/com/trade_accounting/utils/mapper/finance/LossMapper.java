package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.finance.Loss;
import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.models.entity.warehouse.MovementProduct;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LossMapper {
    //Loss
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "lossProductsIds", target = "lossProducts")
    Loss toModel(LossDto lossDto);

    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "lossProductsIds", source = "lossProducts")
    LossDto toDto(Loss loss);

    default Long lossProductToLong(LossProduct lossProducts) {
        return lossProducts.getId();
    }

    default LossProduct longToLossProduct(Long id) {
        return LossProduct.builder()
                .id(id)
                .build();
    }
}
