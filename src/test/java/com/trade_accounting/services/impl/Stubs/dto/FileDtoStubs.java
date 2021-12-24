package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.FileDto;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.FileMapper;
import org.mapstruct.factory.Mappers;

public class FileDtoStubs {
    private static final FileMapper mapper = Mappers.getMapper(FileMapper.class);

    public static FileDto getFileDto(Long id) {
        return mapper.toDto(ModelStubs.getFile(id));
    }
}
