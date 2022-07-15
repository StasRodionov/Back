package com.trade_accounting.utils.mapper.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfAccountingDto;
import com.trade_accounting.models.entity.warehouse.TypeOfAccounting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeOfAccountingMapper {

    TypeOfAccounting toModel(TypeOfAccountingDto typeOfAccountingDto);

    TypeOfAccountingDto toDto(TypeOfAccounting typeOfAccounting);
}
