package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.dto.company.BankAccountDto;
import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    //Company
    @Mapping(source = "addressId", target = "address.id")
    @Mapping(source = "legalDetailDtoId", target = "legalDetail.id")
    @Mapping(source = "bankAccountDtoIds", target = "bankAccounts")
    Company toModel(CompanyDto companyDto);


    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "legalDetail.id", target = "legalDetailDtoId")
    @Mapping(source = "bankAccounts", target = "bankAccountDtoIds")
    CompanyDto toDto(Company company);

    default Long bankAccountToLong(BankAccount bankAccount) {
        return bankAccount.getId();
    }

    default BankAccount longToBankAccount(Long id) {
        return BankAccount.builder()
                .id(id)
                .build();
    }
}
