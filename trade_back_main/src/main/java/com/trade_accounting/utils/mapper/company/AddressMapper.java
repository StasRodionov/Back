package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Address;
import com.trade_accounting.models.dto.company.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    //Adress
    Address toModel(AddressDto address);

    AddressDto toDto(Address address);
}
