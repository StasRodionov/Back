package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.BonusProgram;
import com.trade_accounting.models.dto.util.BonusProgramDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BonusProgramMapper {
    //BonusProgram
    BonusProgram toModel(BonusProgramDto bonusProgramDto);

    BonusProgramDto toDto(BonusProgram bonusProgram);
}
