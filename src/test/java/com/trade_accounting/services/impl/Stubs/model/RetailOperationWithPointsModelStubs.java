package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.RetailOperationWithPoints;

import java.time.LocalDateTime;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getContractor;
import static com.trade_accounting.services.impl.Stubs.model.BonusProgramModelStubs.getBonusProgram;

import static com.trade_accounting.services.impl.Stubs.model.FileModelStubs.getFiles;
import static com.trade_accounting.services.impl.Stubs.model.TaskModelStubs.getTask;

public class RetailOperationWithPointsModelStubs {

    public static RetailOperationWithPoints getRetailOperationWithPoints(Long id) {
        return RetailOperationWithPoints.builder()
                .id(id)
                .number(100L)
                .currentTime(LocalDateTime.now())
                .typeOperation("Начисление")
                .numberOfPoints(10000L)
                .accrualDate(LocalDateTime.now())
                .bonusProgram(getBonusProgram(1L))
                .contractor(getContractor(1L))
                .task(getTask(1L))
                .files(getFiles())
                .build();
    }
}
