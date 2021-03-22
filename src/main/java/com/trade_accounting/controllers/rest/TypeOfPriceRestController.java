package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Type of price Controller", description = "CRUD операции с видами цен")
@Api(tags = "Type of price Rest Controller")
@RequestMapping("/api/typeofprice")
public class TypeOfPriceRestController {

    private final TypeOfPriceService typeOfPriceService;

    public TypeOfPriceRestController(TypeOfPriceService typeOfPriceService) {
        this.typeOfPriceService = typeOfPriceService;
    }
    @GetMapping
    @ApiOperation(value = "getAll", notes = "Возвращает список всех видов цен")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех видов цен"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TypeOfPriceDto>> getAll() {
        List<TypeOfPriceDto> types = typeOfPriceService.getAll();
        log.info("Запрошен список видов цен");
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает определенный вид цен по Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вид цен найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TypeOfPriceDto> getById(@PathVariable(name = "id") Long id) {
        TypeOfPriceDto type = typeOfPriceService.getById(id);
        log.info("Запрошен экземпляр вида цен с id= {}", id);
        return ResponseEntity.ok(type);
    }

    @PostMapping
    @ApiOperation(value = "save", notes = "Создает вид цен на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вид цен успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TypeOfPriceDto> create(@RequestBody TypeOfPriceDto typeOfPriceDto) {
        typeOfPriceService.create(typeOfPriceDto);
        log.info("Записан новый экземпляр вида цен");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновляет вид цен на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вид цен успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TypeOfPriceDto> update(@RequestBody TypeOfPriceDto typeOfPriceDto) {
        typeOfPriceService.update(typeOfPriceDto);
        log.info("Обновлен экземпляр вида цен");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаляет вид цен на основе переданного ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вид цен успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TypeOfPriceDto> deleteById(@PathVariable(name = "id") Long id) {
        typeOfPriceService.deleteById(id);
        log.info("Удален экземпляр Вида цен с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
