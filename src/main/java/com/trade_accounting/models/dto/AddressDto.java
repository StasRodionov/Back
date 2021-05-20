package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String index;
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String another;

    // new AddressDto("123456, г. Москва, ул. Подвойского, д. 14, стр. 7"),
    public AddressDto(Long id, String index, String country, String region, String city, String street, String house, String apartment, String another){

    }
}
