package com.trade_accounting.controllers.rest.indecators;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.services.interfaces.indicators.AuditService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@ApiOperation(value = "deleteById", notes = "Удаляет аудит на основе переданного ID")
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Аудит успешно удален"),
			@ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 404, message = "Данный контролер не найден")}
	)
	public ResponseEntity<AuditDto> deleteById(@ApiParam(
			name = "id",
			type = "Long",
			value = "Переданный ID  в URL по которому необходимо удалить аудит",
			example = "1",
			required = true
	) @PathVariable("id") Long id) {
		auditService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
