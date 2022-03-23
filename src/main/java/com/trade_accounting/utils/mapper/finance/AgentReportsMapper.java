package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

/**
 * @author Pavel Andrusov
 * @since 20.07.2021
 */

@Mapper(componentModel = "spring")
public interface AgentReportsMapper  {
    //AgentsReports
    AgentReports toModel(AgentReportsDto agentReportsDto);

    AgentReportsDto toDto(AgentReports agentReports);
}
