package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.dto.ContractDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    //Contract
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "bankAccount", target = "bankAccountDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto")
    })
     ContractDto contractToContractDto(Contract contract);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "bankAccountDto", target = "bankAccount"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "legalDetailDto", target = "legalDetail")
    })
     Contract contractDtoToContract(ContractDto contractDto);

     List<ContractDto> toContractDtoList(List<Contract> contracts);
}
