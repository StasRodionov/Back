package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.RetailStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "RetailStore Rest Controller", description = "CRUD операции с точками продаж")
@Api(tags = "RetailStore Rest Controller")
@RequestMapping("/api/retail_stores")
public class RetailStoreRestController {

    private final RetailStoreService retailStoreService;
    private final CheckEntityService checkEntityService;

    public RetailStoreRestController(RetailStoreService retailStoreService, CheckEntityService checkEntityService) {
        this.retailStoreService = retailStoreService;
        this.checkEntityService = checkEntityService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех точек продаж")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех точек продаж"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<RetailStoreDto>> getAll() {
        List<RetailStoreDto> retailStoreDtos = retailStoreService.getAll();
        log.info("Запрошен список RetailStoreDto");
        return ResponseEntity.ok(retailStoreDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную точку продаж по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Точка продаж найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<RetailStoreDto> getById(@PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsRetailStoreById(id);
        RetailStoreDto retailStoreDto = retailStoreService.getById(id);
        log.info("Запрошен экземпляр RetailStoreDto с id = {}", id);
        return ResponseEntity.ok(retailStoreDto);
    }
}
