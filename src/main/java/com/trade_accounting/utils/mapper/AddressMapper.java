package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    // Address
    AddressDto toDto(Address address);

    Address toModel(AddressDto address);
}
