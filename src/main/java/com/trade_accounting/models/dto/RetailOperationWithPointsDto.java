package com.trade_accounting.models.dto;


import com.trade_accounting.models.BonusProgram;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.File;
import com.trade_accounting.models.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailOperationWithPointsDto {

    private Long id;

    private Long number;

    private LocalDateTime currentTime;

    private String typeOperation;

    private Long numberOfPoints;

    private LocalDateTime accrualDate;

    @NotNull
    private Long bonusProgramId;

    @NotNull
    private Long contractorId;

    private Long taskId;

    private List<Long> fileIds;
}
