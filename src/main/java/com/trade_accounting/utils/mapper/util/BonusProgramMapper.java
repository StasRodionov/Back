package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.dto.util.BonusProgramDto;
import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.entity.util.BonusProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BonusProgramMapper {
    //BonusProgram
    BonusProgram toModel(BonusProgramDto bonusProgramDto);

    @Mapping(target = "contractorGroupIds", source = "contractorGroups")
    BonusProgramDto toDto(BonusProgram bonusProgram);

    default Long contractorGroupToLong(ContractorGroup contractorGroup) {
        return contractorGroup.getId();
    }
}
