package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.dto.company.ContractorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorMapper {
    //Contractor
    ContractorDto contractorToContractorDto(Contractor contractor);

    Contractor contractorDtoToContractor(ContractorDto contractorDto);
}
