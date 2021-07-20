package com.trade_accounting.services.impl;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.services.interfaces.AgentReportsService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.mapper.AgentReportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AgentReportsServiceImpl implements AgentReportsService {

    private final AgentReportsRepository agentReportsRepository;
    private final AgentReportsMapper agentReportsMapper;

    @Override
    public List<AgentReportsDto> getAll() {
        /*List<AgentReportsDto> res = agentReportsRepository.findAll().stream()
                .map(agentReportsMapper::toDto)
                .collect(Collectors.toList());*/
        List<AgentReportsDto> res = new ArrayList<>();
        AgentReportsDto dto;
        for (AgentReports agentReports : agentReportsRepository.findAll()) {
            dto = agentReportsMapper.toDto(agentReports);
            res.add(dto);
        }

        return res;
    }

    @Override
    public AgentReportsDto getById(Long id) {
        return agentReportsMapper.toDto(agentReportsRepository.findById(id).orElse(new AgentReports()));
    }

    @Override
    public AgentReportsDto create(AgentReportsDto dto) {
        AgentReports agentReports = agentReportsRepository.save(agentReportsMapper.toModel(dto));
        dto.setId(agentReports.getId());
        AgentReportsDto agentReportsDto = agentReportsMapper.toDto(agentReports);
        return agentReportsDto;
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
