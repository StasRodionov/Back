package com.trade_accounting.controllers.rest.company;


import com.trade_accounting.models.dto.company.SaleTaxDto;
import com.trade_accounting.services.interfaces.company.SaleTaxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Sale Tax Rest Controller", description = "CRUD операции со cтавками НДС")
@Api(tags = "Sale Tax Rest Controller")
@RequestMapping("/api/saletax")
@RequiredArgsConstructor
public class SaleTaxRestController {

    private final SaleTaxService saleTaxService;


    @ApiOperation(value = "getAll", notes = "Возвращает список всех ставок НДС")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех ставок НДС"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<SaleTaxDto>> getAll() {
        List<SaleTaxDto> saleTaxDtos = saleTaxService.getAll();
        return ResponseEntity.ok(saleTaxDtos);
    }
}
