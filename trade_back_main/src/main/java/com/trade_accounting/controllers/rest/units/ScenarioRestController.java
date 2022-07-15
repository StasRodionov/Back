package com.trade_accounting.controllers.rest.units;


import com.trade_accounting.models.dto.units.ScenarioDto;
import com.trade_accounting.models.entity.units.Scenario;
import com.trade_accounting.repositories.units.ScenarioRepository;
import com.trade_accounting.services.interfaces.units.ScenarioService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
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
@Tag(name = "Scenario Rest Controller", description = "CRUD операции со сценариями")
@Api(tags = "Scenario Rest Controller")
@RequestMapping("/api/scenario")
@RequiredArgsConstructor
public class ScenarioRestController {

    private final CheckEntityService checkEntityService;
    private final ScenarioService scenarioService;
    private final ScenarioRepository scenarioRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех сценариев")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех сценариев"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<ScenarioDto>> getAll() {
        List<ScenarioDto> scenario = scenarioService.getAll();
        return ResponseEntity.ok(scenario);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает сценарий по его Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сценарий найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ScenarioDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти сценарий",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) scenarioRepository, id);
        return ResponseEntity.ok(scenarioService.getById(id));
    }
    @ApiOperation(value = "create", notes = "Создание нового сценария")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сценарий успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PostMapping
    public ResponseEntity<ScenarioDto> create(@ApiParam(
            name = "scenarioDto",
            value = "DTO сценария,который необходимо создать")@RequestBody ScenarioDto scenarioDto) {
        return ResponseEntity.ok().body(scenarioService.create(scenarioDto));
    }

    @ApiOperation(value = "update", notes = "Обновление информации о сценарии")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о сценарии успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PutMapping
    public ResponseEntity<ScenarioDto> update(@ApiParam(
            name = "scenarioDto",
            value = "DTO сценария, который необходимо обновить")@RequestBody ScenarioDto scenarioDto) {
        return ResponseEntity.ok().body(scenarioService.update(scenarioDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаление сценария по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сценарий удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить сценарий") @PathVariable Long id) {
        scenarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка сценариев по фильтру")
    public ResponseEntity<List<ScenarioDto>> getAll(
            @And({
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
            }) Specification<Scenario> specification) {

        return ResponseEntity.ok(scenarioService.search(specification));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка сценариев по заданным параметрам")
    public ResponseEntity<List<ScenarioDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(scenarioService.searchByString(search));
    }
}
