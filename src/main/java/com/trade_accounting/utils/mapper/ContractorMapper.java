package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ContractorMapper {
    //Contractor
    @Mappings({
            @Mapping(source = "contractorGroup.id", target = "contractorGroupId"),
            @Mapping(source = "typeOfPrice.id", target = "typeOfPriceId"),
            @Mapping(source = "legalDetail", target = "legalDetailDto"),
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "address.id", target = "addressId"),
            @Mapping(source = "contact", target = "contactDto"),
            @Mapping(source = "contractorStatus", target = "contractorStatusDto"),
            @Mapping(source = "accessParameters", target = "accessParametersDto"),
    })
    ContractorDto contractorToContractorDto(Contractor contractor);

    @Mappings({
            @Mapping(source = "contractorGroupId", target = "contractorGroup.id"),
            @Mapping(source = "typeOfPriceId", target = "typeOfPrice.id"),
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailDto", target = "legalDetail"),
            @Mapping(source = "addressId", target = "address.id"),
            @Mapping(source = "contactDto", target = "contact"),
            @Mapping(source = "contractorStatusDto", target = "contractorStatus"),
            @Mapping(source = "accessParametersDto", target = "accessParameters"),
    })
    Contractor contractorDtoToContractor(ContractorDto contractorDto);
}
