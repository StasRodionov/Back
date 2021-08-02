package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.dto.ContractorGroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractorGroupMapper {
    //ContractorGroup
    ContractorGroupDto contractorGroupToContractorGroupDto(ContractorGroup contractorGroup);

    ContractorGroup contractorGroupDtoToContractorGroup(ContractorGroupDto contractorGroupDto);
}
