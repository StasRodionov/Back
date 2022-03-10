package com.trade_accounting.controllers.rest.indicators;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.services.interfaces.indicators.audit.AuditService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
public class AuditController {
	
	private final AuditService auditService;
	
	@ApiOperation(value = "getAll", notes = "Возвращает список всех аудитов")
	@GetMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка всех аудитов"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<AuditDto>> getAll() {
		return ResponseEntity.ok(auditService.getAll());
	}
	
	//todo filter search and quicksearch
}
