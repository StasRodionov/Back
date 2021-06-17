package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.services.interfaces.PriceListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
