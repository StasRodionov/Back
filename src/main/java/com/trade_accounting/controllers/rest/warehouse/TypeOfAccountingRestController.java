package com.trade_accounting.controllers.rest.warehouse;


import com.trade_accounting.models.dto.warehouse.TypeOfAccountingDto;
import com.trade_accounting.repositories.warehouse.TypeOfAccountingRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.TypeOfAccountingService;
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
@Tag(name = "Type Of Accounting Rest Controller", description = "CRUD операции со типами учёта")
@Api(tags = "Type Of Accounting Rest Controller")
@RequestMapping("/api/typeofaccounting")
@RequiredArgsConstructor
public class TypeOfAccountingRestController {

    private final TypeOfAccountingService typeOfAccountingService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех типов учёта")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех типов учёта"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TypeOfAccountingDto>> getAll() {
        List<TypeOfAccountingDto> typeOfAccountingDtos = typeOfAccountingService.getAll();
        return ResponseEntity.ok(typeOfAccountingDtos);
    }
}
