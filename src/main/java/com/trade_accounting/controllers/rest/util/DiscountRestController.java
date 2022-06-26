package com.trade_accounting.controllers.rest.util;


import com.trade_accounting.models.dto.util.DiscountDto;
import com.trade_accounting.services.interfaces.util.DiscountService;
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
@Tag(name = "Discount Rest Controller", description = "CRUD  операции со скидками")
@Api(tags = "Discount Rest Controller")
@RequestMapping("/api/discount")
@RequiredArgsConstructor
public class DiscountRestController {

    DiscountService discountService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех скидок")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех скидок"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    @GetMapping
    public ResponseEntity<List<DiscountDto>> getAll() {
        List<DiscountDto> discounts = discountService.getAll();
        return ResponseEntity.ok(discounts);
    }
}
