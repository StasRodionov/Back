package com.trade_accounting.controllers.rest.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfPackingDto;
import com.trade_accounting.services.interfaces.warehouse.TypeOfPackingService;
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
@Tag(name = "Type Of Packing Rest Controller", description = "CRUD операции с типами фасовки")
@Api(tags = "Type Of Packing Rest Controller")
@RequestMapping("/api/typeofpacking")
@RequiredArgsConstructor
public class TypeOfPackingRestController {

    private final TypeOfPackingService typeOfPackingService;


    @ApiOperation(value = "getAll", notes = "Возвращает список всех типов фасовки")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех типов фасовки"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TypeOfPackingDto>> getAll() {
        List<TypeOfPackingDto> typeOfPackingDtos = typeOfPackingService.getAll();
        return ResponseEntity.ok(typeOfPackingDtos);
    }
}
