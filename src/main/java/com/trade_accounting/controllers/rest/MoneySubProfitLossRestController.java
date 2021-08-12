package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.MoneySubProfitLossDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.MoneySubProfitLossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "MoneySubProfitLoss Rest Controller", description = "Платежи и убытки")
@Api(tags = "MoneySubProfitLoss Rest Controller")
@RequestMapping("api/moneyProfit")
@RequiredArgsConstructor
public class MoneySubProfitLossRestController {

    private final MoneySubProfitLossService moneySubProfitLossService;
    private final CheckEntityService checkEntityService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех возвратов поставщикам")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение возвратов поставщикам"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<MoneySubProfitLossDto>> getAll() {
        return ResponseEntity.ok(moneySubProfitLossService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение возврата поставщику по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Возврат поставщику найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<MoneySubProfitLossDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти возврат поставщику")
                                                         @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsReturnToSupplierById(id);
        return ResponseEntity.ok(moneySubProfitLossService.getById(id));
    }
}