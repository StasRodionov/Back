package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.AgentReportsDto;

public interface AgentReportsMapper {

    default AgentReportsDto toDto(AgentReports model) {
        if (model == null) {
            return null;
        }

        AgentReportsDto.AgentReportsDtoBuilder agentReportsDto = AgentReportsDto.builder();

        agentReportsDto.companyId(model.getCompany().getId());
        agentReportsDto.contractorId(model.getContractor().getId());
        agentReportsDto.id(model.getId());
        agentReportsDto.documentType(model.getDocumentType());
        agentReportsDto.number(model.getNumber());
        agentReportsDto.date(model.getDate());
        agentReportsDto.sum(model.getSum());
        agentReportsDto.remunirationSum(model.getRemunirationSum());
        agentReportsDto.comitentSum(model.getComitentSum());
        agentReportsDto.paid(model.getPaid());
        agentReportsDto.status(model.getStatus());
        agentReportsDto.sent(model.getSent());
        agentReportsDto.printed(model.getPrinted());
        agentReportsDto.commentary(model.getCommentary());

        return agentReportsDto.build();
    }

    default AgentReports toModel(AgentReportsDto dto) {
        if (dto == null) {
            return null;
        }

        AgentReports.AgentReportsBuilder agentReports = AgentReports.builder();

        agentReports.company(Company.builder()
                .id(dto.getId())
                .build());
        agentReports.contractor(Contractor.builder()
                .id(dto.getId())
                .build());
        agentReports.id(dto.getId());
        agentReports.documentType(dto.getDocumentType());
        agentReports.number(dto.getNumber());
        agentReports.date(dto.getDate());
        agentReports.sum(dto.getSum());
        agentReports.remunirationSum(dto.getRemunirationSum());
        agentReports.comitentSum(dto.getComitentSum());
        agentReports.paid(dto.getPaid());
        agentReports.status(dto.getStatus());
        agentReports.sent(dto.getSent());
        agentReports.printed(dto.getPrinted());
        agentReports.commentary(dto.getCommentary());

        return agentReports.build();
    }

}
