package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.models.dto.MovementDto;
import com.trade_accounting.repositories.AcceptanceRepository;
import com.trade_accounting.services.interfaces.AcceptanceService;
import com.trade_accounting.services.interfaces.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
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

@RestController
@Tag(name = "Acceptance Rest Controller", description = "CRUD  операции с приемкой")
@Api(tags = "Acceptance Rest Controller")
@RequestMapping("/api/acceptance")
@RequiredArgsConstructor
public class AcceptanceRestController {
    private final AcceptanceService acceptanceService;
    private final CheckEntityService checkEntityService;
    private final AcceptanceRepository acceptanceRepository;


    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех приемок товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка приемок товара"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<AcceptanceDto>> getAll() {
        return ResponseEntity.ok(acceptanceService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списка всех приемок товара")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка приемок товара"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти приемку")
                                                 @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) acceptanceRepository, id);

        return ResponseEntity.ok(acceptanceService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление новой приемки")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Приемка создана"),
            @ApiResponse(code = 201, message = "Запрос принят и приемка добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceDto> create(@ApiParam(name = "acceptanceDto",
            value = "DTO приемки товара, которое необходимо создать")
                                                @RequestBody AcceptanceDto acceptanceDto) {
        System.out.println("REQUEST!!!!!!!!!!!!!!!!!!!!   " + acceptanceDto);
        return ResponseEntity.ok(acceptanceService.create(acceptanceDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о приемке")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о приемке"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о приемке"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceDto> update(@ApiParam(name = "acceptanceDto",
            value = "DTO приемки, которую необходимо обновить")
                                                @RequestBody AcceptanceDto acceptanceDto) {
        return ResponseEntity.ok(acceptanceService.update(acceptanceDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление приемки по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Приемка удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AcceptanceDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить приемку")
                                                    @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) acceptanceRepository, id);
        acceptanceService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину приемки по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Приемка перенесена в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить приемку")
                                                          @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) acceptanceRepository, id);
        acceptanceService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/restoreFromIsRecyclebin/{id}")
    @ApiOperation(value = "restoreFromIsRecyclebin", notes = "Восстановление приемки по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Приемка восстановлена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MovementDto> restoreFromIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо восстановить приемку")
                                                               @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) acceptanceRepository, id);
        acceptanceService.restoreFromRecyclebin(id);
        return ResponseEntity.ok().build();
    }


}
