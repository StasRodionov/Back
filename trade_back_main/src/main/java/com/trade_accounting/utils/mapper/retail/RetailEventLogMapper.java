package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.dto.retail.RetailEventLogDto;
import com.trade_accounting.models.entity.retail.RetailEventLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RetailEventLogMapper {

    RetailEventLog toModel(RetailEventLogDto retailEventLogDto);

    RetailEventLogDto toDto(RetailEventLog retailEventLog);
}
