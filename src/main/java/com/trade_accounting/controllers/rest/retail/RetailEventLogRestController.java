package com.trade_accounting.controllers.rest.retail;

import com.trade_accounting.models.dto.retail.RetailEventLogDto;
import com.trade_accounting.services.interfaces.retail.RetailEventLogService;
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
@Tag(name = "RetailEventLog Rest Controller", description = "Вывод данных для отображения Журнала событий")
@Api(tags = "RetailEventLog Rest Controller")
@RequestMapping("api/event_log")
@RequiredArgsConstructor
public class RetailEventLogRestController {

    private final RetailEventLogService retailEventLogService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех событий")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка событий"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<RetailEventLogDto>> getAll() {
        return ResponseEntity.ok(retailEventLogService.getAll());
    }
}
