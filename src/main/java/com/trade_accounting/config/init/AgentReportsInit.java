package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.services.interfaces.AgentReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgentReportsInit extends InitData {
    private final AgentReportsService agentReportsService;

    @Override
    void init() {
        //initAgentReports();
    }

    private void initAgentReports() {
        for (Long i = 1L; i <= 5; i++) {
            agentReportsService.create(AgentReportsDto.builder()

                    .build());
        }
    }
}
