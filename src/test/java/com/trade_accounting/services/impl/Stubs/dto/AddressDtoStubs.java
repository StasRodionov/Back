package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.AddressDto;

public class AddressDtoStubs {
    public static AddressDto getAddressDto(Long id){
        return AddressDto.builder()
                .id(id)
                .index("index" + id)
                .country("country" + id)
                .region("region" + id)
                .city("city" + id)
                .street("street" + id)
                .house("house" + id)
                .apartment("apartment" + id)
                .another("another" + id)
                .build();
    }
}
