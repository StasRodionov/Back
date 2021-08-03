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
            @Mapping(source = "legalDetail.id", target = "legalDetailId"),
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "address.id", target = "addressId"),
            @Mapping(source = "contact", target = "contactDto"),
            @Mapping(source = "contractorStatus.id", target = "contractorStatusId"),
            @Mapping(source = "accessParameters.id", target = "accessParametersId"),
    })
    ContractorDto contractorToContractorDto(Contractor contractor);

    @Mappings({
            @Mapping(source = "contractorGroupId", target = "contractorGroup.id"),
            @Mapping(source = "typeOfPriceId", target = "typeOfPrice.id"),
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailId", target = "legalDetail.id"),
            @Mapping(source = "addressId", target = "address.id"),
            @Mapping(source = "contactDto", target = "contact"),
            @Mapping(source = "contractorStatusId", target = "contractorStatus.id"),
            @Mapping(source = "accessParametersId", target = "accessParameters.id"),
    })
    Contractor contractorDtoToContractor(ContractorDto contractorDto);
}
