package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    //Contract
    Contract toModel(ContractDto contractDto);

    ContractDto toDto(Contract contract);
}

