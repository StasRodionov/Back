package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.dto.company.ContractorDto;
import com.trade_accounting.utils.mapper.util.BankAccountToLongMapper;
import com.trade_accounting.utils.mapper.util.ContactToLongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ContactToLongMapper.class, BankAccountToLongMapper.class})
public interface ContractorMapper {
    //Contractor
    Contractor contractorDtoToContractor(ContractorDto contractorDto);

    @Mapping(target = "addressId", source = "contractor.address.id")
    @Mapping(target = "contactIds", source = "contractor.contact")
    @Mapping(target = "contractorGroupId", source = "contractor.contractorGroup.id")
    @Mapping(target = "typeOfPriceId", source = "contractor.typeOfPrice.id")
    @Mapping(target = "bankAccountIds", source = "contractor.bankAccounts")
    @Mapping(target = "legalDetailId", source = "contractor.legalDetail.id")
    @Mapping(target = "contractorStatusId", source = "contractor.contractorStatus.id")
    @Mapping(target = "accessParametersId", source = "contractor.accessParameters.id")
    ContractorDto contractorToContractorDto(Contractor contractor);
}
