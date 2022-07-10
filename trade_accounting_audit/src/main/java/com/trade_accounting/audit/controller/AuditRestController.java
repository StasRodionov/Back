package com.trade_accounting.audit.controller;

import com.trade_accounting.audit.model.Audit;
import com.trade_accounting.audit.service.interfaces.AuditService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/audit")
public class AuditRestController {

    private final AuditService auditService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Возвращает список всех аудитов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех аудитов"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<Audit>> getAll() {
        return ResponseEntity.ok(auditService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает определённый аудит по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные аудита найдены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<Audit> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти валюту",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(auditService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Сохраняет аудит, возвращает аудит с Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные аудита сохранены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<Audit> create(@ApiParam(
            name = "auditDTO",
            value = "auditDTO(id, description, date, employeeId)") @RequestBody Audit audit) {
        return ResponseEntity.ok(auditService.create(audit));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновляет аудит, возвращает обновленный аудит")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные аудита сохранены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<Audit> update(@ApiParam(
            name = "auditDTO",
            value = "auditDTO(id, description, date, employeeId)") @RequestBody Audit audit) {
        return ResponseEntity.ok(auditService.update(audit));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление аудита по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аудит удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить аудит")
                                        @PathVariable(name = "id") Long id) {
        auditService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/quickSearch")
    @ApiOperation(value = "quickSearch", notes = "Получение списка аудитов по заданным параметрам - сотрудник или время")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка аудитов по заданным параметрам"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<Audit>> quickSearch(@RequestParam("search") String search) {
        return ResponseEntity.ok(auditService.quickSearch(search));
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка аудитов по фильтру")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех аудитов по фильтру"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<Audit>> searchByFilter(
            @And({
                    @Spec(path = "description", params = "description", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "employee.id", params = "employeeId", spec = Equal.class),
            }) Specification<Audit> spec) {
        return ResponseEntity.ok(auditService.search(spec));
    }
}
