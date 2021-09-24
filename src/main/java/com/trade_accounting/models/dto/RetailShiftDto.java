package com.trade_accounting.models.dto;


import com.trade_accounting.models.Company;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetailShiftDto {

    private Long id;

    @NotNull
    private String dataOpen;

    private String dataClose;

    @NotNull
    private Long retailStoreId;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long companyId;

    private String bank;

    private BigDecimal revenuePerShift;

    private BigDecimal received;

    private BigDecimal amountOfDiscounts;

    private BigDecimal commission_amount;

    private Boolean sent;

    private Boolean printed;

    private String comment;
}
