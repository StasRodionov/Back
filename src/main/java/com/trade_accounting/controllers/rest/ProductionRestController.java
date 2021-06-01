package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.services.interfaces.ProductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@Tag(name = "Production Rest Controller", description = "CRUD операции с производством")
@Api(tags = "Production Rest Controller")
@RequestMapping("/api/production")
public class ProductionRestController {

    private final ProductionService productionService;

    public ProductionRestController(ProductionService productionService){ this.productionService = productionService; }

    /*
       -getById
       -create
       -update
       -deleteById
     */

    @ApiOperation(value = "getAll", notes = "Возвращает список всех производств")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех производств"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductionDto>> getAll(){
        List<ProductionDto> productionDtoList = productionService.getAll();
        return ResponseEntity.ok(productionDtoList);
    }
    

}
