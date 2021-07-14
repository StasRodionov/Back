package com.trade_accounting.services.impl;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.services.interfaces.AgentReportsService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgentReportsServiceImpl implements AgentReportsService {

    private final AgentReportsRepository agentReportsRepository;

    private final DtoMapper dtoMapper;

    public AgentReportsServiceImpl(AgentReportsRepository agentReportsRepository, DtoMapper dtoMapper) {
        this.agentReportsRepository = agentReportsRepository;
        this.dtoMapper = dtoMapper;
    }

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
        AgentReports model = AgentReports.builder()
                .id(dto.getId())
                .comitentSum(dto.getComitentSum())
                .company(dtoMapper.companyDtoToCompany(dto.getCompanyDto()))
                .commentary(dto.getCommentary())
                .contractor(dtoMapper.contractorDtoToContractor(dto.getContractorDto()))
                .documentType(dto.getDocumentType())
                .number(dto.getNumber())
                .paid(dto.getPaid())
                .printed(dto.getPrinted())
                .sent(dto.getSent())
                .remunirationSum(dto.getRemunirationSum())
                .status(dto.getStatus())
                .sum(dto.getSum())
                .time(dto.getTime())
                .build();
        return dtoMapper.agentReportsToAgentReportsDto(agentReportsRepository.save(model));
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
