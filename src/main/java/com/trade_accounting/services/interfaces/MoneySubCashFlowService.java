package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.MoneySubCashFlowDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

public interface MoneySubCashFlowService {
    List<MoneySubCashFlowDto> getAll();

    List<MoneySubCashFlowDto> filter(LocalDate startDatePeriod, LocalDate endDatePeriod, Long projectId, Long companyId, Long contractorId);

}
