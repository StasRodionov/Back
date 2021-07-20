package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.dto.AgentReportsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Pavel Andrusov
 * @since 20.07.2021
 */

@Mapper(componentModel = "spring")
public interface AgentReportsMapper  {
    @Mappings({
            @Mapping(target = "company", ignore = true),
            @Mapping(target = "contractor", ignore = true)
    })
    AgentReports toModel(AgentReportsDto agentReportsDto);

    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId")
    })
    AgentReportsDto toDto(AgentReports agentReports);
}
