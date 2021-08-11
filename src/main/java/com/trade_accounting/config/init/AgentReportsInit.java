/*
package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.services.interfaces.AgentReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class AgentReportsInit extends InitData {
    private final AgentReportsService agentReportsService;

    @Override
    void init() {
        initAgentReports();
    }

    private void initAgentReports() {
        for (Long i = 1L; i <= 5; i++) {
            agentReportsService.create(AgentReportsDto.builder()
                    .documentType(".odt")
                    .number("1")
                    .time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .companyId(1L)
                    .contractorId(1L)
                    .sum(1L)
                    .remunirationSum(1L)
                    .comitentSum(1L)
                    .paid(1L)
                    .status("Status " + i)
                    .sent(1L)
                    .printed(1L)
                    .commentary("Comment " + 1L)
                    .build());
        }
    }
}
*/
