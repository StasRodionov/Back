package com.trade_accounting.utils.mapper.indicators;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.Indicators.Audit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditMapper {
	Audit auditDtoToAudit(AuditDto auditDto);
	
	AuditDto auditToAuditDto(Audit audit);
}
