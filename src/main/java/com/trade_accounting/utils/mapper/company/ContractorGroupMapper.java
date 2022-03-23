package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.dto.company.ContractorGroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorGroupMapper {
    //ContractorGroup
    ContractorGroup toModel(ContractorGroupDto contractorGroupDto);

    ContractorGroupDto toDto(ContractorGroup contractorGroup);
}
