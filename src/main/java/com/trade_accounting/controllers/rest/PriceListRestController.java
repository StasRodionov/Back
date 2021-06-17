package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.services.interfaces.PriceListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "PriceList Rest Controller", description = "CRUD операции с прайс-листами")
@Api(tags = "PriceList Rest Controller")
@RequestMapping("/api/priceList")
public class PriceListRestController {

    private final PriceListService priceListService;

    public PriceListRestController(PriceListService priceListService){
        this.priceListService = priceListService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех прайс-листов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех прайс-листов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<PriceListDto>> getAll(){
        List<PriceListDto> priceListDtoList = priceListService.getAll();
        return ResponseEntity.ok(priceListDtoList);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный прайс-лист по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<PriceListDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти прайс-лист") @PathVariable(name = "id") Long id){
        PriceListDto priceListDto = priceListService.getById(id);
       return ResponseEntity.ok(priceListDto);
    }

    @ApiOperation(value = "create", notes = "Создает прайс-лист на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<PriceListDto> create(@ApiParam(name = "priceListDto", value = "DTO производства, которое необходимо создать")
                                               @RequestBody PriceListDto priceListDto){
        return ResponseEntity.ok().body(priceListService.create(priceListDto));
    }



}
