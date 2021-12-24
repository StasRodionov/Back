package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.OperationsDto;
import com.trade_accounting.repositories.OperationsRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.OperationsService;
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
@Tag(name = "Operations Rest Controller", description = "Операции со списком документов")
@Api(tags = "Operations Rest Controller")
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationsRestController {
    private final OperationsService operationService;
    private final CheckEntityService checkEntityService;
    private final OperationsRepository operationAbstractRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех перемещений")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка документов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<OperationsDto>> getAll() {
        return ResponseEntity.ok(operationService.getAll());
    }
}
