package com.trade_accounting.controllers.rest.units;

import com.trade_accounting.models.dto.units.ExportDto;
import com.trade_accounting.services.interfaces.units.ExportService;
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
@Tag(name = "Export Rest Controller", description = "CRUD операции с экспортом")
@Api(tags = "Export Rest Controller")
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class ExportRestController {

    private final ExportService exportService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех экспортов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех экспортов"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<ExportDto>> getAll() {
        List<ExportDto> scenario = exportService.getAll();
        return ResponseEntity.ok(scenario);
    }

}
