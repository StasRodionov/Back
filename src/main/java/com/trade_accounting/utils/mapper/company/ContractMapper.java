package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    //Contract
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "bankAccountId", target = "bankAccount.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "legalDetailId", target = "legalDetail.id")
    Contract toModel(ContractDto contractDto);


    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "bankAccountId", source = "bankAccount.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "legalDetailId", source = "legalDetail.id")
    ContractDto toDto(Contract contract);
}

