package com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.dto.util.ColumnsMaskDto;

import java.security.Principal;

public interface ColumnsMaskService {

    ColumnsMaskDto getById(Integer id, Principal principal);

    ColumnsMaskDto update(ColumnsMaskDto dto, Principal principal);

    ColumnsMaskDto create(ColumnsMaskDto dto, Principal principal);
}
