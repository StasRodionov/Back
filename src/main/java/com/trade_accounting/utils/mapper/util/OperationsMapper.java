package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.dto.util.OperationsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationsMapper {
    //Operations
    @Mapping(target = "companyId", source = "company.id")
    OperationsDto toDto(OperationsAbstract operationsAbstract);
}


