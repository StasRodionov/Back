package com.trade_accounting.controllers.rest;

import com.trade_accounting.services.interfaces.AgentReportsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "AgentReports Rest Controller", description = "CRUD операции с отчётами комиссионера")
@Api(tags = "AgentReports Rest Controller")
@RequestMapping("/api/agentReports")
public class AgentReportsRestController {

    private final AgentReportsService agentReportsService;

    public AgentReportsRestController(AgentReportsService agentReportsService){
        this.agentReportsService = agentReportsService;
    }

}
