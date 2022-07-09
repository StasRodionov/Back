package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

/**
 * @author Pavel Andrusov
 * @since 20.07.2021
 */

@Mapper(componentModel = "spring")
public interface AgentReportsMapper  {
    //AgentsReports
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    AgentReports toModel(AgentReportsDto agentReportsDto);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "contractor.id", target = "contractorId")
    AgentReportsDto toDto(AgentReports agentReports);
}
