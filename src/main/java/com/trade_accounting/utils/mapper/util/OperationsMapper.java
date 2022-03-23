package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.dto.util.OperationsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationsMapper {
    //Operations
    OperationsDto toDto(OperationsAbstract operationsAbstract);
}


