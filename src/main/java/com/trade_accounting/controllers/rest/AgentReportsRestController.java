package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.services.interfaces.AgentReportsService;
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
@Tag(name = "AgentReports Rest Controller", description = "CRUD операции с отчётами комиссионера")
@Api(tags = "AgentReports Rest Controller")
@RequestMapping("/api/agentReports")
public class AgentReportsRestController {

    private final AgentReportsService agentReportsService;

    public AgentReportsRestController(AgentReportsService agentReportsService){
        this.agentReportsService = agentReportsService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех отчётов комиссионера")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех отчётов комиссионера"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<AgentReportsDto>> getAll(){
        List<AgentReportsDto> agentReportsDtoList = agentReportsService.getAll();
        return ResponseEntity.ok(agentReportsDtoList);
    }

}
