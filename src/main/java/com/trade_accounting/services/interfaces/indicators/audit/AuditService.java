package com.trade_accounting.services.interfaces.indicators.audit;

import com.trade_accounting.models.dto.indicators.AuditDto;

import java.util.List;

public interface AuditService {
	List<AuditDto> getAll();
	
	AuditDto getById(Long id);
	
	AuditDto create(AuditDto auditDto);
	
	AuditDto update(AuditDto auditDto);
	
	void deleteById(Long id);
}
