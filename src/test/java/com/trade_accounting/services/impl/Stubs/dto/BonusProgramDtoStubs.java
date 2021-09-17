package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.BonusProgramDto;
import com.trade_accounting.services.impl.Stubs.model.BonusProgramModelStubs;
import com.trade_accounting.utils.mapper.BonusProgramMapper;
import org.mapstruct.factory.Mappers;

public class BonusProgramDtoStubs {

    private static final BonusProgramMapper mapper = Mappers.getMapper(BonusProgramMapper.class);

    public static BonusProgramDto getDto(Long id) {
        return mapper.toDto(BonusProgramModelStubs.getBonusProgram(id));
    }
}
