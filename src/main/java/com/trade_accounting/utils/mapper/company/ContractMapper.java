package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    //Contract
    Contract toModel(ContractDto contractDto);


    @Mapping(target = "companyId", source = "contract.company.id")
    @Mapping(target = "bankAccountId", source = "contract.bankAccount.id")
    @Mapping(target = "contractorId", source = "contract.contractor.id")
    @Mapping(target = "legalDetailId", source = "contract.legalDetail.id")
    ContractDto toDto(Contract contract);
}

