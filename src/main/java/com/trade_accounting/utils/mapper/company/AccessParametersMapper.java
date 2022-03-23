package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.AccessParameters;
import com.trade_accounting.models.dto.company.AccessParametersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessParametersMapper {
    //AccessParameters
    AccessParameters toModel(AccessParametersDto accessParametersDto);

    AccessParametersDto toDto(AccessParameters accessParameters);
}
