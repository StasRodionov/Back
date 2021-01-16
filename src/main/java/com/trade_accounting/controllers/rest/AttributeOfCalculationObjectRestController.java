package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Tag(name = "Attribute Of Calculation Object Rest Controller", description = "CRUD операции с объектами")
@Api(tags = "Attribute Of Calculation Object Rest Controller")
@RequestMapping("/api/attribute/calculation/object")
public class AttributeOfCalculationObjectRestController {

    private final AttributeOfCalculationObjectService attributeOfCalculationObjectService;

    @Autowired
    public AttributeOfCalculationObjectRestController(AttributeOfCalculationObjectService attributeOfCalculationObjectService) {
        this.attributeOfCalculationObjectService = attributeOfCalculationObjectService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех объектов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка объектов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<AttributeOfCalculationObjectDto>> getAll(){
        List<AttributeOfCalculationObjectDto> attributeOfCalculationObjectDtos = attributeOfCalculationObjectService.getAll();
        log.info("Запрошен список AttributeOfCalculationObjectDto");
        return ResponseEntity.ok(attributeOfCalculationObjectDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение объекта по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Объект найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<AttributeOfCalculationObjectDto> getById(@PathVariable("id") Long id) {
        AttributeOfCalculationObjectDto attributeOfCalculationObjectDto = attributeOfCalculationObjectService.getById(id);
        log.info("Запрошен экземпляр AttributeOfCalculationObjectDto с id = {}", id);
        return ResponseEntity.ok(attributeOfCalculationObjectDto);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового объекта")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Объект создан"),
            @ApiResponse(code = 201, message = "Запрос принят и объект добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto){
        attributeOfCalculationObjectService.create(attributeOfCalculationObjectDto);
        log.info("Записан новый экземпляр AttributeOfCalculationObjectDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об объекте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о объекте обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об объекте обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto){
        attributeOfCalculationObjectService.update(attributeOfCalculationObjectDto);
        log.info("Обновлен экземплярAttributeOfCalculationObjectDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление объекта по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Объект удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        attributeOfCalculationObjectService.deleteById(id);
        log.info("Удален экземпляр с id = {}", id);
        return ResponseEntity.ok().build();
    }

}
