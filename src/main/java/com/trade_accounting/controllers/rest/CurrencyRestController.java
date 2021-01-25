package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.services.interfaces.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import java.util.Currency;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Currency Rest Controller", description = "CRUD операции с валютами")
@Api(tags = "Currency Rest Controller")
@RequestMapping("/api/currency")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех валют")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех валют"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<CurrencyDto>> getAll() {
        log.info("Запрошен список CurrencyDto");
        return ResponseEntity.ok(currencyService.getAll());
    }

    @ApiOperation(value = "getById", notes = "Возвращает определённую валюту по ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта найдена"),
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
        CurrencyDto currency = currencyService.getById(id);
        log.info("Запрошен экземпляр CurrencyDto с id= {}", id);
        return ResponseEntity.ok(currency);
    }

    @ApiOperation(value = "create", notes = "Создает валюту на основе передданых данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта успешно добавлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "currencyDto",
            value = "DTO валюты, которую необходимо создать") @RequestBody CurrencyDto currencyDto){
        currencyService.create(currencyDto);
        log.info("Записан новый экземпляр Currency с id= {}", currencyDto.getId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update", notes = "Обновляет валюту на основе передданых данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "currencyDto",
            value = "DTO валюты, которую необходимо обновить") @RequestBody CurrencyDto currencyDto) {
        currencyService.update(currencyDto);
        log.info("Обновлен экземпляр с id = {}", currencyDto.getId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет валюту на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Валюта успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить валюту",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        currencyService.deleteById(id);
        log.info("Удален экземпляр с id = {}", id);
        return ResponseEntity.ok().build();
    }


}
