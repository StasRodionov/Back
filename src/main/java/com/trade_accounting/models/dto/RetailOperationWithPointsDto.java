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

    @NotNull
    private Long number;

    @NotNull
    private String currentTime;

    private String typeOperation;

    @NotNull
    private Long numberOfPoints;

    private String accrualDate;

    @NotNull
    private Long bonusProgramId;

    @NotNull
    private Long contractorId;

    private Long taskId;

    private List<Long> fileIds;
}
