package com.trade_accounting.controllers.rest.units;

import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.repositories.units.SalesChannelRepository;
import com.trade_accounting.services.interfaces.units.SalesChannelService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/api/sales-channel")
@RequiredArgsConstructor
@Api(value = "SalesChannelRestController",tags = "SalesChannel Rest Controller")
public class SalesChannelRestController {

    private final SalesChannelService salesChannelService;
    private final SalesChannelRepository salesChannelRepository;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Возвращает список всех каналов продаж.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех каналов продаж"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SalesChannelDto>> getAll(){
        return ResponseEntity.ok(salesChannelService.getAll());
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает канал продаж по его Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Канал продаж найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SalesChannelDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти канал продаж") @PathVariable Long id) {
        checkEntityService.checkExists((JpaRepository) salesChannelRepository, id);
        return ResponseEntity.ok(salesChannelRepository.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового канала продаж")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Канал продаж успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SalesChannelDto> create(@ApiParam(
            name = "salesChannelDto",
            value = "DTO канала продаж, который необходимо создать")@RequestBody SalesChannelDto salesChannelDto){
        return ResponseEntity.ok().body(salesChannelService.create(salesChannelDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Редактирование выбранного канала продаж")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о канале продаж успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SalesChannelDto> update(@ApiParam(
            name = "salesChannelDto",
            value = "DTO канала продаж, который необходимо редактировать") @RequestBody SalesChannelDto salesChannelDto) {
        return ResponseEntity.ok().body(salesChannelService.update(salesChannelDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete", notes = "Удаление выбранного канала продаж")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Канал продаж успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> delete(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить канал продаж") @PathVariable("id") Long id) {
        salesChannelService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
