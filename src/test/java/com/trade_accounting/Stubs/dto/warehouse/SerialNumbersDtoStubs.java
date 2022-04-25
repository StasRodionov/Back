package com.trade_accounting.Stubs.dto.warehouse;

import com.trade_accounting.Stubs.ModelStubs;

import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.utils.mapper.warehouse.SerialNumbersMapper;
import org.mapstruct.factory.Mappers;

public class SerialNumbersDtoStubs {
    private static final SerialNumbersMapper mapper = Mappers.getMapper(SerialNumbersMapper.class);

    public static SerialNumbersDto getDto(Long id){
        return mapper.toDto(ModelStubs.serialNumbers(id));
    }
}
