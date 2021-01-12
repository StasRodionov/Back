package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.services.interfaces.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Role Rest Controller", description = "CRUD операции с ролями")
@Api(tags = "Role Rest Controller")
@RequestMapping("/api/role")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех ролей")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех ролей"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roleDtos = roleService.getAll();
        log.info("Запрошен список RoleDto");
        return ResponseEntity.ok(roleDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную роль по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль найдена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) {
        RoleDto roleDto = roleService.getById(id);
        log.info("Запрошен экземпляр RoleDto с id = {}", id);
        return ResponseEntity.ok(roleDto);
    }

    @ApiOperation(value = "create", notes = "Создает роль на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")}
    )
    public ResponseEntity<?> create(@RequestBody RoleDto roleDto) {
        roleService.create(roleDto);
        log.info("Записан новый экземпляр - {}", roleDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update", notes = "Обновляет роль на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")
            }
    )
    public ResponseEntity<?> update(@RequestBody RoleDto roleDto) {
        roleService.update(roleDto);
        log.info("Обновлен экземпляр RoleDto с id = {}", roleDto.getId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет роль на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Роль успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")
            }
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        log.info("Удален экземпляр RoleDto с id = {}", id);
        return ResponseEntity.ok().build();
    }
}