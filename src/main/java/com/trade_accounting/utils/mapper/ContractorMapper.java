package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contact;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.ContactRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContractorMapper {
    //Contractor
    @Mappings({
            @Mapping(source = "contractorGroup.id", target = "contractorGroupId"),
            @Mapping(source = "typeOfPrice.id", target = "typeOfPriceId"),
            @Mapping(source = "legalDetail.id", target = "legalDetailId"),
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "address.id", target = "addressId"),
//            @Mapping(source = "contact.id", target = "contactIds"),
            @Mapping(source = "contractorStatus.id", target = "contractorStatusId"),
            @Mapping(source = "accessParameters.id", target = "accessParametersId"),
    })
    ContractorDto contractorToContractorDto(Contractor contractor);

    @AfterMapping
    default void listContactIdToListContactIds(Contractor contractor, @MappingTarget ContractorDto contractorDto) {
        if (contractor.getContact() == null) {
            contractorDto.setContactIds(null);
        } else {
            List<Long> contactIds = contractor.getContact().stream()
                    .map(Contact::getId)
                    .collect(Collectors.toList());
            contractorDto.setContactIds(contactIds);
        }
    }

    @Mappings({
            @Mapping(source = "contractorGroupId", target = "contractorGroup.id"),
            @Mapping(source = "typeOfPriceId", target = "typeOfPrice.id"),
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailId", target = "legalDetail.id"),
            @Mapping(source = "addressId", target = "address.id"),
//            @Mapping(source = "contactIds", target = "contact.id"),
            @Mapping(source = "contractorStatusId", target = "contractorStatus.id"),
            @Mapping(source = "accessParametersId", target = "accessParameters.id"),
    })
    Contractor contractorDtoToContractor(ContractorDto contractorDto);


    @AfterMapping
    default void listContactIdsToListContact(ContractorDto contractorDto, @MappingTarget Contractor contractor, @Context ContactRepository contactRepository) {
        if (contractorDto.getContactIds() == null) {
            contractor.setContact(null);
        } else {
            List<Contact> contact = contractorDto.getContactIds()
                    .stream()
                    .map(contactRepository::getOne)
                    .collect(Collectors.toList());
            contractor.setContact(contact);
        }
    }
}
