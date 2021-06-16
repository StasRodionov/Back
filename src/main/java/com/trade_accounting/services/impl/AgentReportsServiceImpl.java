package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.services.interfaces.AgentReportsService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
    }

    @Override
    public AgentReportsDto getById(Long id) {
        return null;
    }

    @Override
    public AgentReportsDto create(AgentReportsDto dto) {
        return null;
    }

    @Override
    public AgentReportsDto update(AgentReportsDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
