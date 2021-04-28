package com.trade_accounting.utils;


import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.OffsetDateTime;

@Mapper
public interface InvoiceMapper {
    @Named("StringToOffsetDateTime")
    public static OffsetDateTime stringToOffsetDateTime(String date) {
        return OffsetDateTime.parse(date);
    }
}
