package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.dto.company.ContractorDto;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContractorMapper {
    //Contractor
    ContractorDto contractorToContractorDto(Contractor contractor);

    Contractor contractorDtoToContractor(ContractorDto contractorDto);
}
