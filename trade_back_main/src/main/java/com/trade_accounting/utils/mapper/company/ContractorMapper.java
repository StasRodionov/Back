package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.dto.company.ContractorDto;
import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.entity.company.Contractor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractorMapper {

    @Mapping(source = "addressId", target = "address.id")
    @Mapping(source = "contractorGroupId", target = "contractorGroup.id")
    @Mapping(source = "typeOfPriceId", target = "typeOfPrice.id")
    @Mapping(source = "legalDetailId", target = "legalDetail.id")
    @Mapping(source = "contractorStatusId", target = "contractorStatus.id")
    @Mapping(source = "accessParametersId", target = "accessParameters.id")
    @Mapping(source = "accountId", target = "account.id")
    Contractor contractorDtoToContractor(ContractorDto contractorDto);

    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "contactIds", source = "contact")
    @Mapping(target = "contractorGroupId", source = "contractorGroup.id")
    @Mapping(target = "typeOfPriceId", source = "typeOfPrice.id")
    @Mapping(target = "bankAccountIds", source = "bankAccounts")
    @Mapping(target = "legalDetailId", source = "legalDetail.id")
    @Mapping(target = "contractorStatusId", source = "contractorStatus.id")
    @Mapping(target = "accessParametersId", source = "accessParameters.id")
    @Mapping(source = "account.id", target = "accountId")
    ContractorDto contractorToContractorDto(Contractor contractor);

    default Long bankAccountToLong(BankAccount bankAccount) {
        return bankAccount.getId();
    }

    default Long contactToLong(Contact contact) {
        return contact.getId();
    }

}
