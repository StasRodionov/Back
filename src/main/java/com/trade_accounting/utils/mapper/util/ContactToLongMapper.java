package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.company.Contact;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ContactToLongMapper {

    default Long contactToLong(Contact contact) {
        return contact.getId();
    }
}
