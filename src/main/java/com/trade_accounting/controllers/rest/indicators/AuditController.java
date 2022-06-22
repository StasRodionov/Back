package com.trade_accounting.controllers.rest.indicators;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.dto.units.CurrencyDto;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.repositories.indicators.AuditRepository;
import com.trade_accounting.services.interfaces.indicators.audit.AuditService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
public class AuditController {

	private final AuditService auditService;
	private final CheckEntityService checkEntityService;
	private final AuditRepository auditRepository;

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

	@ApiOperation(value = "getById", notes = "Возвращает определённый аудит по ID")
	@GetMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Данные аудита найдены"),
			@ApiResponse(code = 404, message = "Данный контроллер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<CurrencyDto> getById(@ApiParam(
			name = "id",
			type = "Long",
			value = "ID переданный в URL по которому необходимо найти валюту",
			example = "1",
			required = true) @PathVariable(name = "id") Long id) {
		checkEntityService.checkExists((JpaRepository) auditRepository, id);
		return ResponseEntity.ok(auditRepository.getById(id));
	}

	@ApiOperation(value = "quickSearch", notes = "Получение списка аудитов по заданным параметрам - сотрудник или время")
	@GetMapping("/quickSearch")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка аудитов по заданным параметрам"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<AuditDto>> quickSearch(@RequestParam("search") String search) {
		return ResponseEntity.ok(auditService.quickSearch(search));
	}

	@ApiOperation(value = "searchByFilter", notes = "Получение списка аудитов по фильтру")
	@GetMapping("/searchByFilter")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Успешное получение списка всех аудитов по фильтру"),
			@ApiResponse(code = 404, message = "Данный контролер не найден"),
			@ApiResponse(code = 403, message = "Операция запрещена"),
			@ApiResponse(code = 401, message = "Нет доступа к данной операции")}
	)
	public ResponseEntity<List<AuditDto>> searchByFilter(
			@And({
					@Spec(path = "description", params = "description", spec = Equal.class),
					@Spec(path = "date", params = "date", spec = Equal.class),
					@Spec(path = "employee.id", params = "employeeId", spec = Equal.class),
			}) Specification<Audit> spec) {
		return ResponseEntity.ok(auditService.search(spec));
	}
}
