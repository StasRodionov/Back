package com.trade_accounting.services.impl;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.services.interfaces.AgentReportsService;
import com.trade_accounting.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AgentReportsServiceImpl implements AgentReportsService {

    private final AgentReportsRepository agentReportsRepository;

    private final CompanyRepository companyRepository;

    private final ContractorRepository contractorRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<AgentReportsDto> getAll() {
        return agentReportsRepository.findAll().stream()
                .map(dtoMapper::agentReportsToAgentReportsDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgentReportsDto getById(Long id) {
        return dtoMapper.agentReportsToAgentReportsDto(agentReportsRepository.findById(id).orElse(new AgentReports()));
    }

    @Override
    public AgentReportsDto create(AgentReportsDto dto) {
        AgentReports agentReports = AgentReports.builder()
                .id(dto.getId())
                .company(companyRepository.getCompaniesById(1L))
                .contractor(contractorRepository.getOne(1L))
                .comitentSum(dto.getComitentSum())
                .date(dto.getDate())
                .commentary(dto.getCommentary())
                .documentType(dto.getDocumentType())
                .number(dto.getNumber())
                .paid(dto.getPaid())
                .printed(dto.getPrinted())
                .sent(dto.getSent())
                .remunirationSum(dto.getRemunirationSum())
                .status(dto.getStatus())
                .sum(dto.getSum())
                .build();
        return dtoMapper.agentReportsToAgentReportsDto(agentReportsRepository.save(agentReports));
    }

    @Override
    public AgentReportsDto update(AgentReportsDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        agentReportsRepository.deleteById(id);
    }
}
