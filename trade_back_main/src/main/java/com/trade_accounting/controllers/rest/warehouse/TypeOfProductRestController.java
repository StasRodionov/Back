package com.trade_accounting.controllers.rest.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfProductDto;
import com.trade_accounting.services.interfaces.warehouse.TypeOfProductService;
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
@Tag(name = "Type Of Product Rest Controller", description = "CRUD операции со типами товаров и комплектов")
@Api(tags = "Type Of Product Rest Controller")
@RequestMapping("/api/typeofproduct")
@RequiredArgsConstructor
public class TypeOfProductRestController {

    private final TypeOfProductService typeOfProductService;


    @ApiOperation(value = "getAll", notes = "Возвращает список всех типов товаров и комплектов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех типов товаров и комплектов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TypeOfProductDto>> getAll() {
        List<TypeOfProductDto> typeOfProductDtos = typeOfProductService.getAll();
        return ResponseEntity.ok(typeOfProductDtos);
    }
}
