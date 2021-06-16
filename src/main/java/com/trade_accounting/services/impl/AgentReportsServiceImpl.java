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

    public AgentReportsServiceImpl(AgentReportsRepository agentReportsRepository, DtoMapper dtoMapper){
        this.agentReportsRepository = agentReportsRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<AgentReportsDto> getAll() {
        final List<AgentReportsDto> collect = agentReportsRepository.findAll().stream()
                .map(dtoMapper::agentReportsToAgentReportsDto)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public AgentReportsDto getById(Long id) {
        return dtoMapper.agentReportsToAgentReportsDto(agentReportsRepository.findById(id).orElse(new AgentReports()));
    }

    @Override
    public AgentReportsDto create(AgentReportsDto dto) {
        AgentReports agentReports = agentReportsRepository.save(dtoMapper.agentReportsDtoToAgentReports(dto));
        dto.setId(agentReports.getId());
        return dtoMapper.agentReportsToAgentReportsDto(agentReports);
    }

    @Override
    public AgentReportsDto update(AgentReportsDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
