package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.ContractorStatus;
import com.trade_accounting.models.dto.company.ContractorStatusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorStatusMapper {
    //Contractors
    ContractorStatus toModel(ContractorStatusDto status);

    ContractorStatusDto toDto(ContractorStatus contractorStatus);

}
