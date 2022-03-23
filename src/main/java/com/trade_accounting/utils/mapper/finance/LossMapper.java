package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.finance.Loss;
import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LossMapper {
    //Loss
    Loss toModel(LossDto lossDto);

    LossDto toDto(Loss loss);
}
